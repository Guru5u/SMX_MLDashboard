package com.qts.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.ProcCpu;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarProxy;
import org.hyperic.sigar.SigarProxyCache;
import org.hyperic.sigar.cmd.Ps;
import org.hyperic.sigar.cmd.Shell;
import org.hyperic.sigar.ptql.ProcessFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qts.model.DiskStatistics;
import com.qts.model.JvmStatistics;
import com.qts.model.Memory;
import com.qts.model.NetworkInterfaceDetails;
import com.qts.model.NetworkInterfaceStats;
import com.qts.model.NetworkInterfaces;
import com.qts.model.OperatingSystemDetails;
import com.qts.model.Process;
import com.qts.model.Swap;

public class JvmStatisticsUtility {
	private static final Logger logger = LoggerFactory.getLogger(JvmStatistics.class);
	public static final int SLEEP_TIME = 2000;
	
	public static final int CPU_HISTORY_QUEUE_LENGTH = 50;
	public static final int NETWORK_HISTORY_QUEUE_LENGTH = 50;
	public static final int PROCESS_HISTORY_QUEUE_LENGTH = 20;
	public static final int MEMORY_SWAP_HISTORY_QUEUE_LENGTH = 20;
	public static final int MEMORY_DURATION = 120000;			// 300000
	public static final int PROCESS_DURATION = 300000;			// 300000
	
	//---CPU History
	private static final LinkedList<Double> userCpuHistory = new LinkedList<Double>();
	private static final LinkedList<Double> systemCpuHistory = new LinkedList<Double>();
	
	//---Memory History
	private static final LinkedList<Memory> memoryHistory = new LinkedList<Memory>();

	//---Swap History
	private static final LinkedList<Swap> swapHistory = new LinkedList<Swap>();
	
	//---Process History
	private static final LinkedList<Process> processHistory = new LinkedList<Process>();

	private Sigar sigarImpl;
	private SigarProxy sigar;
	private Shell shell;
	private CpuInfo cpuinfo;
	private OperatingSystem operatingSystem;

	private static class Holder {
		private static final JvmStatisticsUtility instance = new JvmStatisticsUtility();
	}

	private JvmStatisticsUtility() {
		logger.debug("***java.library.path=" + System.getProperty("java.library.path").toString());
		sigarImpl = new Sigar();
		sigar = SigarProxyCache.newInstance(sigarImpl, SLEEP_TIME);
		
		shell = new Shell();

		CpuInfo[] infos = null;

		try {
			infos = sigar.getCpuInfoList();
		} catch (SigarException e) {
			e.printStackTrace();
		}
		cpuinfo = infos[0];
		operatingSystem = OperatingSystem.getInstance();
	}

	public static JvmStatisticsUtility getInstance() {
		return Holder.instance;
	}

	private List<Long> getPidsWithQuery(final String query) throws SigarException {
		ProcessFinder finder = new ProcessFinder(sigar);
		long[] results = finder.find(query);
		List<Long> list = new ArrayList<Long>(results.length);
		for (final long result : results) {
			list.add(result);
		}
		return list;
	}

	double roundTo2Decimals(double val) {
		DecimalFormat df2 = new DecimalFormat("###.00");
		return Double.valueOf(df2.format(val));
	}

	private void addUserCpuHistory(double value) {
		value = roundTo2Decimals(value);

		if (userCpuHistory.size() >= CPU_HISTORY_QUEUE_LENGTH) {
			userCpuHistory.remove();
			userCpuHistory.add(value);
		} else {
			userCpuHistory.add(value);
		}
	}

	private void addSystemCpuHistory(double value) {
		value = roundTo2Decimals(value);

		if (systemCpuHistory.size() >= CPU_HISTORY_QUEUE_LENGTH) {
			systemCpuHistory.remove();
			systemCpuHistory.add(value);
		} else {
			systemCpuHistory.add(value);
		}
	}

	private void addMemoryHistory(long memAvg, long memUsed, long memFree) {
		long now = System.currentTimeMillis();
		
		Memory memory = new Memory(memAvg, memUsed, memFree);
		
		if (memory != null) {
			logger.info("Inside addMemoryHistory...: " + memory); 
			if (memoryHistory.isEmpty()) {
				memoryHistory.add(memory);
			} else {
				Memory lastMemory = memoryHistory.peekLast();
				if (now > lastMemory.getCreationTime() + MEMORY_DURATION) {
					if (memoryHistory.size() >= MEMORY_SWAP_HISTORY_QUEUE_LENGTH) {
						memoryHistory.remove();
						memoryHistory.add(memory);
					} else {
						memoryHistory.add(memory);
					}
				}
			}
		}
	}
	
	private void addSwapHistory(long swapAvg, long swapUsed, long swapFree) {
		long now = System.currentTimeMillis();
		
		Swap swap = new Swap(swapAvg, swapUsed, swapFree);
		if (swap != null) {
			logger.info("Inside addSwapHistory...: " + swap); 
			if (swapHistory.isEmpty()) {
				swapHistory.add(swap);
			} else {
				Swap lastSwap = swapHistory.peekLast();
				if (now > lastSwap.getCreationTime() + MEMORY_DURATION) {
					if (swapHistory.size() >= MEMORY_SWAP_HISTORY_QUEUE_LENGTH) {
						swapHistory.remove();
						swapHistory.add(swap);
					} else {
						swapHistory.add(swap);
					}
				}
			}
		}
	}
	
	private void addProcessHistory(long threads, long stopped_procs, long idle_procs,
			long sleeping_procs, long running_procs, long zombie_procs, long total_procs) {
		
		long now = System.currentTimeMillis();					
		Process process = new Process(threads, stopped_procs, idle_procs, sleeping_procs, running_procs,
				zombie_procs, total_procs, now);
		
		if (process != null) {
			logger.info("Inside addMemoryHistory...: " + process); 
			if (processHistory.isEmpty()) {
				processHistory.add(process);
			} else {
				Process lastProcess = processHistory.peekLast();
				if (now > lastProcess.getCreationTime() + PROCESS_DURATION) {
					if (processHistory.size() >= PROCESS_HISTORY_QUEUE_LENGTH) {
						processHistory.remove();
						processHistory.add(process);
					} else {
						processHistory.add(process);
					}
				}
			}
		}
	}
	
	public NetworkInterfaces getAllNetInterfaces(boolean isActive) {
		NetworkInterfaces interfaces;
		ArrayList<String> activeInterfaces = new ArrayList<String>();
		
		try {
			interfaces = new NetworkInterfaces();
			String[] iface = sigar.getNetInterfaceList();
			if (isActive) {
				for (String net: iface) {
					NetInterfaceStat netStat = sigar.getNetInterfaceStat(net);
					if (netStat.getRxBytes() > 0) {
						activeInterfaces.add(net);
					}
				}
				interfaces.setNetworkInterface(activeInterfaces);
			} else {
				interfaces.setNetworkInterface(iface);
			}
			return interfaces;
		} catch (SigarException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public FileSystem[] getAllFileSystemList() {
		FileSystem[] fsList;
		try {
			fsList = sigar.getFileSystemList();
			return fsList;
		} catch (SigarException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public JvmStatistics getJVMStatistics(String args) throws SigarException {
		logger.info("Inside getJVMStatistics...");

		JvmStatistics jvm = new JvmStatistics();
		jvm.setCPUVendor(cpuinfo.getVendor());
		jvm.setCPUModel(cpuinfo.getModel());
		jvm.setCPUMhz(String.format("%d", cpuinfo.getMhz()));
		
		jvm.setRunningProcs(sigar.getProcStat().getRunning());
		jvm.setSleepingProcs(sigar.getProcStat().getSleeping());
		jvm.setStoppedProcs(sigar.getProcStat().getStopped());
		jvm.setThreads(sigar.getProcStat().getThreads());
		jvm.setIdleProcs(sigar.getProcStat().getIdle());
		jvm.setZombieProcs(sigar.getProcStat().getZombie());
		jvm.setTotalProcs(sigar.getProcStat().getTotal());
		addProcessHistory(sigar.getProcStat().getThreads(), sigar.getProcStat().getStopped(), sigar.getProcStat().getIdle(),
							sigar.getProcStat().getSleeping(), sigar.getProcStat().getRunning(), 
							sigar.getProcStat().getZombie(), sigar.getProcStat().getTotal());
		jvm.setProcessHistory(processHistory.toArray(new Process[processHistory.size()]));
		
		jvm.setSystemCpu(sigar.getCpuPerc().getSys() * 100.0);
		jvm.setUserCpu(sigar.getCpuPerc().getUser() * 100.0);
		addSystemCpuHistory(sigar.getCpuPerc().getSys() * 100.0);
		addUserCpuHistory(sigar.getCpuPerc().getUser() * 100.0);

		jvm.setSystemCpuHistory(systemCpuHistory.toArray(new Double[systemCpuHistory.size()]));
		jvm.setUserCpuHistory(userCpuHistory.toArray(new Double[userCpuHistory.size()]));
		jvm.setWaitCpu(sigar.getCpuPerc().getWait() * 100.0);
		jvm.setNiceCpu(sigar.getCpuPerc().getNice() * 100.0);
		jvm.setIdleCpu(sigar.getCpuPerc().getIdle() * 100.0);

		jvm.setTotalMem(sigar.getMem().getTotal());
		jvm.setUsedMem(sigar.getMem().getUsed());
		jvm.setFreeMem(sigar.getMem().getFree());
		addMemoryHistory(sigar.getMem().getTotal(), sigar.getMem().getUsed(), sigar.getMem().getFree());
	//	jvm.setMemoryHistory(memoryHistory.toArray(new Memory[memoryHistory.size()]));
		
		jvm.setFreeMemPercent(sigar.getMem().getFreePercent());
		jvm.setUserMemPrecent(sigar.getMem().getUsedPercent());

		jvm.setTotalSwap(sigar.getSwap().getTotal());
		jvm.setUsedSwap(sigar.getSwap().getUsed());
		jvm.setFreeSwap(sigar.getSwap().getFree());
		addSwapHistory(sigar.getSwap().getTotal(), sigar.getSwap().getUsed(), sigar.getSwap().getFree());
		//jvm.setSwapHistory(swapHistory.toArray(new Swap[swapHistory.size()]));
		
		logger.debug(sigar.getProcStat().toString());
		logger.debug(sigar.getCpuPerc().toString());
		logger.debug(sigar.getMem().toString());
		logger.debug(sigar.getSwap().toString());

		//NetStat netstat = sigar.getNetStat();
		/*logger.info("getAllInboundTotal: " + sigar.getNetStat().getAllInboundTotal());
		logger.info("getAllOutboundTotal: " + sigar.getNetStat().getAllOutboundTotal());
		logger.info("getTcpBound: " + sigar.getNetStat().getTcpBound());
		logger.info("getTcpClose: " + sigar.getNetStat().getTcpClose());
		logger.info("getTcpCloseWait: " + sigar.getNetStat().getTcpCloseWait());
		logger.info("getTcpClosing: " + sigar.getNetStat().getTcpClosing());
		logger.info("getTcpEstablished: " + sigar.getNetStat().getTcpEstablished());
		logger.info("getTcpFinWait1: " + sigar.getNetStat().getTcpFinWait1());
		logger.info("getTcpFinWait2: " + sigar.getNetStat().getTcpFinWait2());
		logger.info("getTcpIdle: " + sigar.getNetStat().getTcpIdle());
		logger.info("getTcpInboundTotal: " + sigar.getNetStat().getTcpInboundTotal());
		logger.info("getTcpLastAck: " + sigar.getNetStat().getTcpLastAck());
		logger.info("getTcpListen: " + sigar.getNetStat().getTcpListen());
		logger.info("getTcpOutboundTotal: " + sigar.getNetStat().getTcpOutboundTotal());
		logger.info("getTcpSynRecv: " + sigar.getNetStat().getTcpSynRecv());
		logger.info("getTcpSynSent: " + sigar.getNetStat().getTcpSynSent());
		logger.info("getTcpTimeWait: " + sigar.getNetStat().getTcpTimeWait());
		logger.info("-------------------------------------------------");*/
		
		List<Long> pids = getPidsWithQuery(args);
		// shell.getPids(sigar, args);
		for (int i = 0; i < pids.size(); i++) {
			long pid = pids.get(i);
			String cpuPerc = "?";
			List list;
			try {
				list = Ps.getInfo(sigar, pid);
			} catch (SigarException e) {
				continue;
			}

			try {
				ProcCpu cpu = sigar.getProcCpu(pid);
				cpuPerc = CpuPerc.format(cpu.getPercent());
			} catch (SigarException e) {
			}
			list.add(list.size() - 1, cpuPerc);
			logger.debug(Ps.join(list).toString());
		}

		
		/*long cacheSize = info.getCacheSize();
		logger.info("Vendor........." + info.getVendor());
		logger.info("Model.........." + info.getModel());
		logger.info("Mhz............" + info.getMhz());
		logger.info("Total CPUs....." + info.getTotalCores());
		if ((info.getTotalCores() != info.getTotalSockets())
				|| (info.getCoresPerSocket() > info.getTotalCores())) {
			logger.info("Physical CPUs.." + info.getTotalSockets());
			logger.info("Cores per CPU.." + info.getCoresPerSocket());
		}
		if (cacheSize != Sigar.FIELD_NOTIMPL) {
			logger.info("Cache size...." + cacheSize);
		}
		for (int i = 0; i < cpus.length; i++) {
			logger.info("CPU " + i + ".........");
			logger.info(cpus[i].toString());
		}
		logger.info("Totals........");
		logger.info(this.sigar.getCpuPerc().toString());*/
		 

		/*MultiProcCpu cpu = sigar.getMultiProcCpu(args);
		logger.info("Number of processors: " + cpu.getProcesses());

		logger.info("Cpu usage: " + CpuPerc.format(cpu.getPercent()));
		logger.info("Cpu getTotal: " + CpuPerc.format(cpu.getTotal()));
		logger.info("Cpu getUser: " + CpuPerc.format(cpu.getUser()));
		logger.info("Cpu getSys: " + CpuPerc.format(cpu.getSys()));
		logger.info("Cpu time: " + Ps.getCpuTime(cpu.getTotal()));*/

		return jvm;
	}	
	
	public OperatingSystemDetails getOSDetails() {
		logger.info("Inside OperatingSystemDetails...");
	
		OperatingSystemDetails osDetails = new OperatingSystemDetails();
		osDetails.setOSDescription(operatingSystem.getDescription());
		osDetails.setOSArchitecture(operatingSystem.getArch());
		osDetails.setOSVendor(operatingSystem.getVendor());
		osDetails.setOSPatchLevel(operatingSystem.getPatchLevel());
		osDetails.setOSCodeName(operatingSystem.getVendorCodeName());
		osDetails.setOSDataModel(operatingSystem.getDataModel());
		osDetails.setOSName(operatingSystem.getName());
		osDetails.setOSVersion(operatingSystem.getVersion());
		
		osDetails.setJVMVersion(System.getProperty("java.vm.version"));
		osDetails.setJVMVendor(System.getProperty("java.vm.vendor"));
		osDetails.setJVMHome(System.getProperty("java.home"));
		
		return osDetails;
	}
	
	public NetworkInterfaceDetails getNetInterfaceDetails(String netInterface) throws SigarException {
		NetworkInterfaceDetails netDetails = new NetworkInterfaceDetails();
		
		NetInterfaceConfig netInfo = sigar.getNetInterfaceConfig(netInterface);
		netDetails.setAddress(netInfo.getAddress());
		netDetails.setBroadcast(netInfo.getBroadcast());
		netDetails.setDescription(netInfo.getDescription());
		netDetails.setDestination(netInfo.getDestination());
		netDetails.setHwAddress(netInfo.getHwaddr());
		netDetails.setFlags(netInfo.getFlags());
		netDetails.setMetric(netInfo.getMetric());
		netDetails.setMTU(netInfo.getMtu());
		netDetails.setName(netInfo.getName());
		netDetails.setNetmask(netInfo.getNetmask());
		netDetails.setType(netInfo.getType());
	
		return netDetails;
	}
	
	public DiskStatistics getDiskDetails(String drive) throws SigarException {
		FileSystemUsage fsInfo = sigar.getFileSystemUsage(drive);
		
		DiskStatistics diskStatistics =new DiskStatistics();
		diskStatistics.setAvailableBytes(fsInfo.getAvail());
		diskStatistics.setDiskQueue(fsInfo.getDiskQueue());
		diskStatistics.setDiskReadBytes(fsInfo.getDiskReadBytes());
		diskStatistics.setDiskReads(fsInfo.getDiskReads());
		diskStatistics.setDiskServiceTime(fsInfo.getDiskServiceTime());
		diskStatistics.setDiskWriteBytes(fsInfo.getDiskWriteBytes());
		diskStatistics.setFreeBytes(fsInfo.getFree());
		diskStatistics.setFreeFiles(fsInfo.getFreeFiles());
		diskStatistics.setTotalFiles(fsInfo.getTotal());
		diskStatistics.setUsePercentage(fsInfo.getUsePercent());
		diskStatistics.setUsedBytes(fsInfo.getUsed());
		
		return diskStatistics;
	}
	
	public NetworkInterfaceStats getNetworkInterfaceStats(String netInterface) throws SigarException {
		NetInterfaceStat netStat = sigar.getNetInterfaceStat(netInterface);
		
		NetworkInterfaceStats netInterfaceStats = new NetworkInterfaceStats();
		netInterfaceStats.setRxBytes(netStat.getRxBytes());
		netInterfaceStats.setRxDroppped(netStat.getRxDropped());
		netInterfaceStats.setRxErrors(netStat.getRxErrors());
		netInterfaceStats.setRxFrame(netStat.getRxFrame());
		netInterfaceStats.setRxOverruns(netStat.getRxOverruns());
		netInterfaceStats.setRxPackets(netStat.getRxPackets());
		netInterfaceStats.setSpeed(netStat.getSpeed());
		netInterfaceStats.setTxBytes(netStat.getTxBytes());
		netInterfaceStats.setTxCarrier(netStat.getTxCarrier());
		netInterfaceStats.setTxCollisions(netStat.getTxCollisions());
		netInterfaceStats.setTxDropped(netStat.getTxDropped());
		netInterfaceStats.setTxErrors(netStat.getTxErrors());
		netInterfaceStats.setTxOverruns(netStat.getTxOverruns());
		netInterfaceStats.setTxPackets(netStat.getTxPackets());
		
		return netInterfaceStats;
	}
}
