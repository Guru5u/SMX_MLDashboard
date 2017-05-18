package com.qts.model;

public class Process {
	private long threads;
	private long stopped_procs;
	private long idle_procs;
	private long sleeping_procs;
	private long running_procs;
	private long zombie_procs;
	private long total_procs;
	private long creationTime;
	
	public long getThreads() {
		return threads;
	}
	
	public Process(long threads, long stopped_procs, long idle_procs,
			long sleeping_procs, long running_procs, long zombie_procs,
			long total_procs, long creationTime) {
		super();
		this.threads = threads;
		this.stopped_procs = stopped_procs;
		this.idle_procs = idle_procs;
		this.sleeping_procs = sleeping_procs;
		this.running_procs = running_procs;
		this.zombie_procs = zombie_procs;
		this.total_procs = total_procs;
		this.creationTime = creationTime;
	}

	public void setThreads(long threads) {
		this.threads = threads;
	}
	
	public long getStopped_procs() {
		return stopped_procs;
	}
	
	public void setStopped_procs(long stopped_procs) {
		this.stopped_procs = stopped_procs;
	}
	
	public long getIdle_procs() {
		return idle_procs;
	}
	
	public void setIdle_procs(long idle_procs) {
		this.idle_procs = idle_procs;
	}
	
	public long getSleeping_procs() {
		return sleeping_procs;
	}
	
	public void setSleeping_procs(long sleeping_procs) {
		this.sleeping_procs = sleeping_procs;
	}
	
	public long getRunning_procs() {
		return running_procs;
	}
	
	public void setRunning_procs(long running_procs) {
		this.running_procs = running_procs;
	}
	
	public long getZombie_procs() {
		return zombie_procs;
	}
	
	public void setZombie_procs(long zombie_procs) {
		this.zombie_procs = zombie_procs;
	}
	
	public long getTotal_procs() {
		return total_procs;
	}
	
	public void setTotal_procs(long total_procs) {
		this.total_procs = total_procs;
	}
	
	public long getCreationTime() {
		return creationTime;
	}
	
	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}

	@Override
	public String toString() {
		return "Process [threads=" + threads + ", stopped_procs="
			+ stopped_procs + ", idle_procs=" + idle_procs
			+ ", sleeping_procs=" + sleeping_procs + ", running_procs="
			+ running_procs + ", zombie_procs=" + zombie_procs
			+ ", total_procs=" + total_procs + ", creationTime="
			+ creationTime + "]";
	}
	
	
}
