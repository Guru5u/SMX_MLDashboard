package com.qts.model;

import javax.xml.bind.annotation.XmlElement;

import org.codehaus.jackson.annotate.JsonProperty;

@SuppressWarnings("restriction")
public class DiskData {
	private String dirName;
	private String devName;
	private String typeName;
	private String sysTypeName;
	private String options;
	private int type;
	private long flags;
	private boolean isOnline;
	private double usedPercentage;
	
	@XmlElement(name="dir_name") 
	@JsonProperty("dir_name")
	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	@XmlElement(name="dev_name") 
	@JsonProperty("dev_name")
	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	@XmlElement(name="type_name") 
	@JsonProperty("type_name")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@XmlElement(name="sys_type_name") 
	@JsonProperty("sys_type_name")
	public String getSysTypeName() {
		return sysTypeName;
	}

	public void setSysTypeName(String sysTypeName) {
		this.sysTypeName = sysTypeName;
	}

	@XmlElement(name="options") 
	@JsonProperty("options")
	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	@XmlElement(name="type") 
	@JsonProperty("type")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@XmlElement(name="is_online") 
	@JsonProperty("is_online")
	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	@XmlElement(name="flags") 
	@JsonProperty("flags")	
	public long getFlags() {
		return flags;
	}

	public void setFlags(long flags) {
		this.flags = flags;
	}

	@XmlElement(name="used_percentage") 
	@JsonProperty("used_percentage")
	public double getUsedPercentage() {
		return usedPercentage;
	}
	
	public void setUsedPercentage(double usedPercentage) {
		this.usedPercentage = usedPercentage;
	}

	@Override
	public String toString() {
		return "DiskData [dirName=" + dirName + ", devName=" + devName
				+ ", typeName=" + typeName + ", sysTypeName=" + sysTypeName
				+ ", options=" + options + ", type=" + type + ", flags="
				+ flags + ", isOnline=" + isOnline + ", usedPercentage="
				+ usedPercentage + "]";
	}
	
	
}
