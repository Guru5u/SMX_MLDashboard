package com.qts.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement(name = "network_interface_details")
public class NetworkInterfaceDetails {
	private String Address;
	private String Description;
	private String Destination;
	private String Broadcast;
	private long Flags;
	private String HwAddress;
	private long Metric;
	private long MTU;
	private String Name;
	private String Type;
	private String Netmask;
	
	@XmlElement(name="interface_address") 
	@JsonProperty("interface_address")
	public String getAddress() {
		return Address;
	}
	
	public void setAddress(String address) {
		Address = address;
	}

	@XmlElement(name="interface_description") 
	@JsonProperty("interface_description")
	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	@XmlElement(name="interface_destination") 
	@JsonProperty("interface_destination")
	public String getDestination() {
		return Destination;
	}

	public void setDestination(String destination) {
		Destination = destination;
	}

	@XmlElement(name="interface_broadcast") 
	@JsonProperty("interface_broadcast")
	public String getBroadcast() {
		return Broadcast;
	}

	public void setBroadcast(String broadcast) {
		Broadcast = broadcast;
	}

	@XmlElement(name="interface_flags") 
	@JsonProperty("interface_flags")
	public long getFlags() {
		return Flags;
	}

	public void setFlags(long flags) {
		Flags = flags;
	}

	@XmlElement(name="interface_hwaddr") 
	@JsonProperty("interface_hwaddr")
	public String getHwAddress() {
		return HwAddress;
	}

	public void setHwAddress(String hwAddress) {
		HwAddress = hwAddress;
	}

	@XmlElement(name="interface_metric") 
	@JsonProperty("interface_metric")
	public long getMetric() {
		return Metric;
	}

	public void setMetric(long metric) {
		Metric = metric;
	}

	@XmlElement(name="interface_mtu") 
	@JsonProperty("interface_mtu")
	public long getMTU() {
		return MTU;
	}

	public void setMTU(long mTU) {
		MTU = mTU;
	}

	@XmlElement(name="interface_name") 
	@JsonProperty("interface_name")
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	@XmlElement(name="interface_type") 
	@JsonProperty("interface_type")
	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	@XmlElement(name="interface_netmask") 
	@JsonProperty("interface_netmask")
	public String getNetmask() {
		return Netmask;
	}

	public void setNetmask(String netmask) {
		Netmask = netmask;
	}

	@Override
	public String toString() {
		return "NetworkInterfaceDetails [Address=" + Address 
			+ ", Description=" + Description + ", Destination="
			+ Destination + ", Broadcast=" + Broadcast + ", Flags=" 
			+ Flags + ", HwAddress=" + HwAddress + ", Metric=" 
			+ Metric + ", MTU=" + MTU + ", Name=" + Name 
			+ ", Type=" + Type + ", Netmask=" + Netmask + "]";
	}
}
