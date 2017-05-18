package com.qts.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement(name = "disk_statistics")
public class DiskStatistics {
	private long availableBytes;
	private double diskQueue;
	private long diskReadBytes;
	private long diskReads;
	private double diskServiceTime;
	private long diskWriteBytes;
	private long freeBytes;
	private long freeFiles;
	private long totalFiles;
	private long usedBytes;
	private double usePercentage;
	
	@XmlElement(name="available_bytes") 
	@JsonProperty("available_bytes")
	public long getAvailableBytes() {
		return availableBytes;
	}
	
	public void setAvailableBytes(long availableBytes) {
		this.availableBytes = availableBytes;
	}

	@XmlElement(name="disk_queue") 
	@JsonProperty("disk_queue")
	public double getDiskQueue() {
		return diskQueue;
	}

	public void setDiskQueue(double diskQueue) {
		this.diskQueue = diskQueue;
	}

	@XmlElement(name="disk_read_bytes") 
	@JsonProperty("disk_read_bytes")
	public long getDiskReadBytes() {
		return diskReadBytes;
	}

	public void setDiskReadBytes(long diskReadBytes) {
		this.diskReadBytes = diskReadBytes;
	}

	@XmlElement(name="disk_reads") 
	@JsonProperty("disk_reads")
	public long getDiskReads() {
		return diskReads;
	}

	public void setDiskReads(long diskReads) {
		this.diskReads = diskReads;
	}

	@XmlElement(name="disk_service_time") 
	@JsonProperty("disk_service_time")
	public double getDiskServiceTime() {
		return diskServiceTime;
	}

	public void setDiskServiceTime(double diskServiceTime) {
		this.diskServiceTime = diskServiceTime;
	}

	@XmlElement(name="disk_write_bytes") 
	@JsonProperty("disk_write_bytes")
	public long getDiskWriteBytes() {
		return diskWriteBytes;
	}

	public void setDiskWriteBytes(long diskWriteBytes) {
		this.diskWriteBytes = diskWriteBytes;
	}

	@XmlElement(name="free_bytes") 
	@JsonProperty("free_bytes")
	public long getFreeBytes() {
		return freeBytes;
	}

	public void setFreeBytes(long freeBytes) {
		this.freeBytes = freeBytes;
	}

	@XmlElement(name="free_files") 
	@JsonProperty("free_files")
	public long getFreeFiles() {
		return freeFiles;
	}

	public void setFreeFiles(long freeFiles) {
		this.freeFiles = freeFiles;
	}

	@XmlElement(name="total_files") 
	@JsonProperty("total_files")
	public long getTotalFiles() {
		return totalFiles;
	}

	public void setTotalFiles(long totalFiles) {
		this.totalFiles = totalFiles;
	}

	@XmlElement(name="used_bytes") 
	@JsonProperty("used_bytes")
	public long getUsedBytes() {
		return usedBytes;
	}

	public void setUsedBytes(long usedBytes) {
		this.usedBytes = usedBytes;
	}

	@XmlElement(name="use_percentage") 
	@JsonProperty("use_percentage")
	public double getUsePercentage() {
		return usePercentage;
	}

	public void setUsePercentage(double usePercentage) {
		this.usePercentage = usePercentage;
	}

	@Override
	public String toString() {
		return "DiskStatistics [availableBytes=" + availableBytes + ", diskQueue=" + diskQueue + ", diskReadBytes="
				+ diskReadBytes + ", diskReads=" + diskReads + ", diskServiceTime=" + diskServiceTime
				+ ", diskWriteBytes=" + diskWriteBytes + ", freeBytes=" + freeBytes + ", freeFiles=" + freeFiles
				+ ", totalFiles=" + totalFiles + ", usedBytes=" + usedBytes + ", usePercentage=" + usePercentage + "]";
	}
	
	
}
