package com.qts.model;

public class Memory {
	private long memoryTotal;
	private long memoryUsed;
	private long memoryFree;
	private long creationTime;
	
	
	public Memory(long memoryTotal, long memoryUsed, long memoryFree) {
		long now = System.currentTimeMillis();
		
		this.memoryTotal = memoryTotal;
		this.memoryUsed = memoryUsed;
		this.memoryFree = memoryFree;
		this.creationTime = now;
	}

	public long getMemoryTotal() {
		return memoryTotal;
	}
	
	public void setMemoryTotal(long memoryTotal) {
		this.memoryTotal = memoryTotal;
	}
	
	public long getMemoryUsed() {
		return memoryUsed;
	}
	
	public void setMemoryUsed(long memoryUsed) {
		this.memoryUsed = memoryUsed;
	}
	
	public long getMemoryFree() {
		return memoryFree;
	}
	
	public void setMemoryFree(long memoryFree) {
		this.memoryFree = memoryFree;
	}
	
	public long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}
	
	@Override
	public String toString() {
		return "Memory [memoryTotal=" + memoryTotal + ", memoryUsed="
				+ memoryUsed + ", memoryFree=" + memoryFree + ", creationTime="
				+ creationTime + "]";
	}

}
