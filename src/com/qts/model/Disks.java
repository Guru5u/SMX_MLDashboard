package com.qts.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

import com.qts.model.DiskData;

@XmlRootElement
public class Disks {
	private ArrayList<DiskData> diskData = new ArrayList<DiskData>();

	@XmlElement(name="disk") 
	@JsonProperty("disk")
	public ArrayList<DiskData> getDiskData() {
		return diskData;
	}
	
	public void addDrive(String dirName, String devName, String typeName,
			String sysTypeName, String options, int type, long flags, boolean isOnline, double usedPercent) {
		DiskData data = new DiskData();
		data.setDirName(dirName);
		data.setDevName(devName);
		data.setTypeName(typeName);
		data.setSysTypeName(sysTypeName);
		data.setOptions(options);
		data.setType(type);
		data.setFlags(flags);
		data.setOnline(isOnline);
		data.setUsedPercentage(usedPercent);
		diskData.add(data);
	}

	@Override
	public String toString() {
		return "AllDisks [diskData=" + diskData + "]";
	}
}
