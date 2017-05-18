package com.qts.model;

public class Swap {
	private long swapTotal;
	private long swapUsed;
	private long swapFree;
	private long creationTime;
	
	public Swap(long swapTotal, long swapUsed, long swapFree) {
		long now = System.currentTimeMillis();
		
		this.swapTotal = swapTotal;
		this.swapUsed = swapUsed;
		this.swapFree = swapFree;
		this.creationTime = now;
	}
	
	public long getSwapTotal() {
		return swapTotal;
	}	

	public void setSwapTotal(long swapTotal) {
		this.swapTotal = swapTotal;
	}
	
	public long getSwapUsed() {
		return swapUsed;
	}
	
	public void setSwapUsed(long swapUsed) {
		this.swapUsed = swapUsed;
	}
	
	public long getSwapFree() {
		return swapFree;
	}
	
	public void setSwapFree(long swapFree) {
		this.swapFree = swapFree;
	}

	public long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}

	@Override
	public String toString() {
		return "Swap [swapTotal=" + swapTotal 
			+ ", swapUsed=" + swapUsed + ", swapFree=" 
			+ swapFree + ", creationTime=" + creationTime
			+ "]";
	}
}