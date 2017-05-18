package com.qts.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement(name = "network_interface_stats")
public class NetworkInterfaceStats {
	private long RxBytes;
	private long RxDroppped;
	private long RxErrors;
	private long RxFrame;
	private long RxOverruns;
	private long RxPackets;
	private long Speed;
	private long TxBytes;
	private long TxCarrier;
	private long TxCollisions;
	private long TxDropped;
	private long TxErrors;
	private long TxOverruns;
	private long TxPackets;
	
	@XmlElement(name="rx_bytes") 
	@JsonProperty("rx_bytes")
	public long getRxBytes() {
		return RxBytes;
	}
	
	public void setRxBytes(long rxBytes) {
		RxBytes = rxBytes;
	}

	@XmlElement(name="rx_dropped") 
	@JsonProperty("rx_dropped")
	public long getRxDroppped() {
		return RxDroppped;
	}

	public void setRxDroppped(long rxDroppped) {
		RxDroppped = rxDroppped;
	}

	@XmlElement(name="rx_errors") 
	@JsonProperty("rx_errors")
	public long getRxErrors() {
		return RxErrors;
	}

	public void setRxErrors(long rxErrors) {
		RxErrors = rxErrors;
	}

	@XmlElement(name="rx_frame") 
	@JsonProperty("rx_frame")
	public long getRxFrame() {
		return RxFrame;
	}

	public void setRxFrame(long rxFrame) {
		RxFrame = rxFrame;
	}

	@XmlElement(name="rx_overruns") 
	@JsonProperty("rx_overruns")
	public long getRxOverruns() {
		return RxOverruns;
	}

	public void setRxOverruns(long rxOverruns) {
		RxOverruns = rxOverruns;
	}

	@XmlElement(name="rx_packets") 
	@JsonProperty("rx_packets")
	public long getRxPackets() {
		return RxPackets;
	}

	public void setRxPackets(long rxPackets) {
		RxPackets = rxPackets;
	}

	@XmlElement(name="speed") 
	@JsonProperty("speed")
	public long getSpeed() {
		return Speed;
	}

	public void setSpeed(long speed) {
		Speed = speed;
	}

	@XmlElement(name="tx_bytes") 
	@JsonProperty("tx_bytes")
	public long getTxBytes() {
		return TxBytes;
	}

	public void setTxBytes(long txBytes) {
		TxBytes = txBytes;
	}

	@XmlElement(name="tx_carrier") 
	@JsonProperty("tx_carrier")
	public long getTxCarrier() {
		return TxCarrier;
	}

	public void setTxCarrier(long txCarrier) {
		TxCarrier = txCarrier;
	}

	@XmlElement(name="tx_collisions") 
	@JsonProperty("tx_collisions")
	public long getTxCollisions() {
		return TxCollisions;
	}

	public void setTxCollisions(long txCollisions) {
		TxCollisions = txCollisions;
	}

	@XmlElement(name="tx_droppped") 
	@JsonProperty("tx_droppped")
	public long getTxDropped() {
		return TxDropped;
	}

	public void setTxDropped(long txDropped) {
		TxDropped = txDropped;
	}

	@XmlElement(name="tx_errors") 
	@JsonProperty("tx_errors")
	public long getTxErrors() {
		return TxErrors;
	}

	public void setTxErrors(long txErrors) {
		TxErrors = txErrors;
	}

	@XmlElement(name="tx_overruns") 
	@JsonProperty("tx_overruns")
	public long getTxOverruns() {
		return TxOverruns;
	}

	public void setTxOverruns(long txOverruns) {
		TxOverruns = txOverruns;
	}

	@XmlElement(name="tx_packets") 
	@JsonProperty("tx_packets")
	public long getTxPackets() {
		return TxPackets;
	}

	public void setTxPackets(long txPackets) {
		TxPackets = txPackets;
	}

	@Override
	public String toString() {
		return "NetworkInterfaceStats [RxBytes=" + RxBytes 
			+ ", RxDroppped=" + RxDroppped + ", RxErrors=" 
			+ RxErrors + ", RxFrame=" + RxFrame + ", RxOverruns=" 
			+ RxOverruns + ", RxPackets=" + RxPackets 
			+ ", Speed=" + Speed + ", TxBytes=" + TxBytes
			+ ", TxCarrier=" + TxCarrier + ", TxCollisions=" 
			+ TxCollisions + ", TxDropped=" + TxDropped 
			+ ", TxErrors=" + TxErrors + ", TxOverruns=" 
			+ TxOverruns + ", TxPackets=" + TxPackets + "]";
	}
	
	
}
