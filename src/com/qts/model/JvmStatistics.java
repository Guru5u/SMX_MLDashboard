package com.qts.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

import com.qts.model.Memory;
import com.qts.model.Process;
import com.qts.model.Swap;

@XmlRootElement(name = "jvm_statistics")
public class JvmStatistics implements Serializable {
	private static final long serialVersionUID = -12837283723827389L;
	private String CPUVendor;
	private String CPUModel;
	private String CPUMhz;
	
	// {Stopped=0, Threads=1678, Idle=0, Sleeping=0, Running=117, Zombie=0, Total=117}
	private long stoppedProcs;
	private long idleProcs;
	private long sleepingProcs;
	private long runningProcs;
	private long zombieProcs;
	private long totalProcs;
	private long threads;

	// CPU states: 7.7% user, 3.8% system, 0.0% nice, 0.0% wait, 87.5% idle
	private double userCpu;
	private double systemCpu;
	private double niceCpu;
	private double waitCpu;
	private double idleCpu;

	// Mem: 16685724K av, 4893048K used, 11792676K free
	private long totalMem;
	private long usedMem;
	private long freeMem;
	private double freeMemPercent;
	private double userMemPrecent;

	// Swap: 19700380K av, 5501760K used, 14198620K free
	private long totalSwap;
	private long usedSwap;
	private long freeSwap;
	
	private Double[] userCpuHistory;
	private Double[] systemCpuHistory;
	private Process[] processHistory;
	
	private Memory[] memoryHistory;
	private Swap[] swapHistory;
	
	@XmlElement(name="cpu_vendor") 
	@JsonProperty("cpu_vendor")
	public String getCPUVendor() {
		return CPUVendor;
	}

	public void setCPUVendor(String vendor) {
		CPUVendor = vendor;
	}

	@XmlElement(name="cpu_model") 
	@JsonProperty("cpu_model")
	public String getCPUModel() {
		return CPUModel;
	}

	public void setCPUModel(String model) {
		CPUModel = model;
	}

	@XmlElement(name="cpu_mhz") 
	@JsonProperty("cpu_mhz")
	public String getCPUMhz() {
		return CPUMhz;
	}

	public void setCPUMhz(String mhz) {
		CPUMhz = mhz;
	}

	@XmlElement(name="stopped_procs") 
	@JsonProperty("stopped_procs")
	public long getStoppedProcs() {
		return stoppedProcs;
	}

	public void setStoppedProcs(long stoppedProcs) {
		this.stoppedProcs = stoppedProcs;
	}

	@XmlElement(name="idle_procs") 
	@JsonProperty("idle_procs")
	public long getIdleProcs() {
		return idleProcs;
	}

	public void setIdleProcs(long idleProcs) {
		this.idleProcs = idleProcs;
	}

	@XmlElement(name="sleeping_procs") 
	@JsonProperty("sleeping_procs")
	public long getSleepingProcs() {
		return sleepingProcs;
	}

	public void setSleepingProcs(long sleepingProcs) {
		this.sleepingProcs = sleepingProcs;
	}

	@XmlElement(name="running_procs") 
	@JsonProperty("running_procs")
	public long getRunningProcs() {
		return runningProcs;
	}

	public void setRunningProcs(long runningProcs) {
		this.runningProcs = runningProcs;
	}

	@XmlElement(name="zombie_procs") 
	@JsonProperty("zombie_procs")
	public long getZombieProcs() {
		return zombieProcs;
	}

	public void setZombieProcs(long zombieProcs) {
		this.zombieProcs = zombieProcs;
	}

	@XmlElement(name="total_procs") 
	@JsonProperty("total_procs")
	public long getTotalProcs() {
		return totalProcs;
	}

	public void setTotalProcs(long totalProcs) {
		this.totalProcs = totalProcs;
	}

	public long getThreads() {
		return threads;
	}

	public void setThreads(long threads) {
		this.threads = threads;
	}

	@XmlElement(name="user_cpu") 
	@JsonProperty("user_cpu")
	public double getUserCpu() {
		return userCpu;
	}

	public void setUserCpu(double userCpu) {
		this.userCpu = userCpu;
	}

	@XmlElement(name="system_cpu") 
	@JsonProperty("system_cpu")
	public double getSystemCpu() {
		return systemCpu;
	}

	public void setSystemCpu(double systemCpu) {
		this.systemCpu = systemCpu;
	}

	@XmlElement(name="nice_cpu") 
	@JsonProperty("nice_cpu")
	public double getNiceCpu() {
		return niceCpu;
	}

	public void setNiceCpu(double niceCpu) {
		this.niceCpu = niceCpu;
	}

	@XmlElement(name="wait_cpu") 
	@JsonProperty("wait_cpu")
	public double getWaitCpu() {
		return waitCpu;
	}

	public void setWaitCpu(double waitCpu) {
		this.waitCpu = waitCpu;
	}

	@XmlElement(name="idle_cpu") 
	@JsonProperty("idle_cpu")
	public double getIdleCpu() {
		return idleCpu;
	}

	public void setIdleCpu(double idleCpu) {
		this.idleCpu = idleCpu;
	}

	@XmlElement(name="total_mem") 
	@JsonProperty("total_mem")
	public long getTotalMem() {
		return totalMem;
	}

	public void setTotalMem(long totalMem) {
		this.totalMem = totalMem;
	}

	@XmlElement(name="used_mem") 
	@JsonProperty("used_mem")
	public long getUsedMem() {
		return usedMem;
	}

	public void setUsedMem(long usedMem) {
		this.usedMem = usedMem;
	}

	@XmlElement(name="free_mem") 
	@JsonProperty("free_mem")
	public long getFreeMem() {
		return freeMem;
	}

	public void setFreeMem(long freeMem) {
		this.freeMem = freeMem;
	}

	@XmlElement(name="free_mem_percent") 
	@JsonProperty("free_mem_percent")
	public double getFreeMemPercent() {
		return freeMemPercent;
	}

	public void setFreeMemPercent(double freeMemPercent) {
		this.freeMemPercent = freeMemPercent;
	}
	
	@XmlElement(name="user_mem_percent") 
	@JsonProperty("user_mem_percent")
	public double getUserMemPrecent() {
		return userMemPrecent;
	}

	
	public void setUserMemPrecent(double userMemPrecent) {
		this.userMemPrecent = userMemPrecent;
	}
	
	@XmlElement(name="total_swap") 
	@JsonProperty("total_swap")
	public long getTotalSwap() {
		return totalSwap;
	}
	
	public void setTotalSwap(long totalSwap) {
		this.totalSwap = totalSwap;
	}

	@XmlElement(name="used_swap") 
	@JsonProperty("used_swap")
	public long getUsedSwap() {
		return usedSwap;
	}
	
	public void setUsedSwap(long usedSwap) {
		this.usedSwap = usedSwap;
	}

	@XmlElement(name="free_swap") 
	@JsonProperty("free_swap")
	public long getFreeSwap() {
		return freeSwap;
	}

	public void setFreeSwap(long freeSwap) {
		this.freeSwap = freeSwap;
	}

	@XmlElement(name="user_cpu_history") 
	@JsonProperty("user_cpu_history")
	public Double[] getUserCpuHistory() {
		return userCpuHistory;
	}

	public void setUserCpuHistory(Double[] userCpuHistory) {
		this.userCpuHistory = userCpuHistory;
	}

	@XmlElement(name="process_history") 
	@JsonProperty("process_history")
	public Process[] getProcessHistory() {
		return processHistory;
	}

	public void setProcessHistory(Process[] processHistory) {
		this.processHistory = processHistory;
	}

	@XmlElement(name="system_cpu_history") 
	@JsonProperty("system_cpu_history")
	public Double[] getSystemCpuHistory() {
		return systemCpuHistory;
	}

	public void setSystemCpuHistory(Double[] systemCpuHistory) {
		this.systemCpuHistory = systemCpuHistory;
	}

	@XmlElement(name="memory_history") 
	@JsonProperty("memory_history")
	public Memory[] getMemoryHistory() {
		return memoryHistory;
	}

	public void setMemoryHistory(Memory[] memoryHistory) {
		this.memoryHistory = memoryHistory;
	}

	@XmlElement(name="swap_history") 
	@JsonProperty("swap_history")
	public Swap[] getSwapHistory() {
		return swapHistory;
	}

	public void setSwapHistory(Swap[] swapHistory) {
		this.swapHistory = swapHistory;
	}

	@Override
	public String toString() {
		return "JvmStatistics [CPUVendor=" + CPUVendor + ", CPUModel=" 
				+ CPUModel + ", CPUMhz=" + CPUMhz + ", stoppedProcs=" 
				+ stoppedProcs + ", idleProcs=" + idleProcs  + ", sleepingProcs=" 
				+ sleepingProcs + ", runningProcs=" + runningProcs 
				+ ", zombieProcs=" + zombieProcs + ", totalProcs=" + totalProcs
				+ ", threads=" + threads + ", userCpu=" + userCpu 
				+ ", systemCpu=" + systemCpu + ", niceCpu=" + niceCpu
				+ ", waitCpu=" + waitCpu + ", idleCpu=" + idleCpu 
				+ ", totalMem=" + totalMem + ", usedMem=" + usedMem
				+ ", freeMem=" + freeMem + ", freeMemPercent=" + freeMemPercent 
				+ ", userMemPrecent=" + userMemPrecent + ", totalSwap=" 
				+ totalSwap + ", usedSwap=" + usedSwap + ", freeSwap=" 
				+ freeSwap + ", userCpuHistory=" + Arrays.toString(userCpuHistory) 
				+ ", systemCpuHistory=" + Arrays.toString(systemCpuHistory)
				+ ", processHistory=" + Arrays.toString(processHistory) 
				+ ", memoryHistory=" + Arrays.toString(memoryHistory) 
				+ ", swapHistory=" + Arrays.toString(swapHistory) + "]";
	}
}
