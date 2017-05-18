package com.qts.model;

import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement(name = "network_interfaces")
public class NetworkInterfaces {
	private ArrayList<String> interfaces = null;
	
	public void setNetworkInterface(String[] iface) {
		interfaces = new ArrayList<String>(Arrays.asList(iface));		
	}
	
	public void setNetworkInterface(ArrayList<String> iface) {
		interfaces = new ArrayList<String>();
		interfaces.addAll(iface);
	}

	@JsonProperty("interface")
	@XmlElement(name="interface") 
	public ArrayList<String> getNetworkInterface() {
		return interfaces;
	}
}
