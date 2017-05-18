package com.qts.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement(name = "operating_system_details")
public class OperatingSystemDetails {
	// Operating System Details
	private String OSDescription;
	private String OSArchitecture;
	private String OSName;
	private String OSVersion;
	private String OSCodeName;
	private String OSDataModel;
	private String OSVendor;
	private String OSPatchLevel;
	private String JVMVersion;
	private String JVMVendor;
	private String JVMHome;
	
	@XmlElement(name="os_description") 
	@JsonProperty("os_description")
	public String getOSDescription() {
		return OSDescription;
	}

	public void setOSDescription(String oSDescription) {
		OSDescription = oSDescription;
	}

	@XmlElement(name="os_architecture") 
	@JsonProperty("os_architecture")	
	public String getOSArchitecture() {
		return OSArchitecture;
	}

	public void setOSArchitecture(String oSArchitecture) {
		OSArchitecture = oSArchitecture;
	}

	@XmlElement(name="os_vendor") 
	@JsonProperty("os_vendor")
	public String getOSVendor() {
		return OSVendor;
	}

	public void setOSVendor(String oSVendor) {
		OSVendor = oSVendor;
	}

	@XmlElement(name="os_patch_level") 
	@JsonProperty("os_patch_level")
	public String getOSPatchLevel() {
		return OSPatchLevel;
	}

	public void setOSPatchLevel(String oSPatchLevel) {
		OSPatchLevel = oSPatchLevel;
	}

	@XmlElement(name="os_name") 
	@JsonProperty("os_name")
	public String getOSName() {
		return OSName;
	}

	public void setOSName(String oSName) {
		OSName = oSName;
	}

	@XmlElement(name="os_version") 
	@JsonProperty("os_version")
	public String getOSVersion() {
		return OSVersion;
	}

	public void setOSVersion(String oSVersion) {
		OSVersion = oSVersion;
	}

	@XmlElement(name="os_code_name") 
	@JsonProperty("os_code_name")
	public String getOSCodeName() {
		return OSCodeName;
	}

	public void setOSCodeName(String oSCodeName) {
		OSCodeName = oSCodeName;
	}

	@XmlElement(name="os_data_model") 
	@JsonProperty("os_data_model")
	public String getOSDataModel() {
		return OSDataModel;
	}

	public void setOSDataModel(String oSDataModel) {
		OSDataModel = oSDataModel;
	}

	@XmlElement(name="jvm_version") 
	@JsonProperty("jvm_version")
	public String getJVMVersion() {
		return JVMVersion;
	}

	public void setJVMVersion(String jVMVersion) {
		JVMVersion = jVMVersion;
	}

	@XmlElement(name="jvm_vendor") 
	@JsonProperty("jvm_vendor")
	public String getJVMVendor() {
		return JVMVendor;
	}

	public void setJVMVendor(String jVMVendor) {
		JVMVendor = jVMVendor;
	}

	@XmlElement(name="jvm_home") 
	@JsonProperty("jvm_home")
	public String getJVMHome() {
		return JVMHome;
	}

	public void setJVMHome(String jVMHome) {
		JVMHome = jVMHome;
	}

	@Override
	public String toString() {
		return "cpuDetails [OSDescription=" + OSDescription 
			+ ", OSArchitecture=" + OSArchitecture + ", OSName="
			+ OSName + ", OSVersion=" + OSVersion + ", OSCodeName=" 
			+ OSCodeName + ", OSDataModel=" + OSDataModel 
			+ ", OSVendor=" + OSVendor + ", OSPatchLevel=" 
			+ OSPatchLevel + ", JVMVersion=" + JVMVersion 
			+ ", JVMVendor=" + JVMVendor + ", JVMHome=" + JVMHome + "]";
	}

	
}
