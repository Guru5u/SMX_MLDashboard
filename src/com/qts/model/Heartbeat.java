package com.qts.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.qts.util.JsonDateSerializer;

@XmlRootElement(name = "heartbeat")
public class Heartbeat {
	private static final long serialVersionUID = -127298392839023L;
	private int count;  
	private String ipAddress;
	private Date startDate;
	private Date currentDate;
	
	SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy 'at' h:mm:ss a");
	
	public Heartbeat() {
		count = 1;
		startDate = new Date();
		currentDate = new Date();
	}

	@XmlElement(name="count") 
	@JsonProperty("count")
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@XmlElement(name="ip_address") 
	@JsonProperty("ip_address")
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@XmlElement(name="start_date") 
	@JsonProperty("start_date")
	public String getStartDate() {
		return df.format(startDate);
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@XmlElement(name="current_date") 
	@JsonProperty("current_date")
	public String getCurrentDate() {
		return df.format(currentDate);
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	@XmlElement(name="start_date_long") 
	@JsonProperty("start_date_long")
	public long getStartDateLong() {
		return startDate.getTime();
	}

	@XmlElement(name="current_date_long") 
	@JsonProperty("current_date_long")
	public long getCurrentDateLong() {
		return currentDate.getTime();
	}

	@Override
	public String toString() {
		return "Heartbeat [count=" + count + ", ipAddress=" + ipAddress
				+ ", startDate=" + startDate + ", currentDate=" + currentDate
				+ "]";
	}	
}
