package com.qts.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@XmlRootElement(name = "statistics")
public class Statistics  implements Serializable {
	private static final long serialVersionUID = -1982982909309202L;

	@Id private String id;
    private String todayHeading;
	private int todayCount;
	private double todayAverage;
	private String todayAverageSubheading;
	private String onboardedHeading;
	private int onboardedCount;
	private String onboardedSubheading;
	private String signupsHeading;
	private int signupsCount;
	private String signupsSubheading;

	@XmlElement(name="id", type=String.class) 
	@JsonProperty("id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@XmlElement(name="today_heading", type=String.class) 
	@JsonProperty("today_heading")
	public String getTodayHeading() {
		return todayHeading;
	}

	public void setTodayHeading(String todayHeading) {
		this.todayHeading = todayHeading;
	}

	@XmlElement(name="today_count") 
	@JsonProperty("today_count")
	public int getTodayCount() {
		return todayCount;
	}

	public void setTodayCount(int todayCount) {
		this.todayCount = todayCount;
	}

	@XmlElement(name="today_avg") 
	@JsonProperty("today_avg")
	public double getTodayAverage() {
		return todayAverage;
	}

	public void setTodayAverage(double todayAverage) {
		this.todayAverage = todayAverage;
	}

	@XmlElement(name="today_avg_subheading", type=String.class) 
	@JsonProperty("today_avg_subheading")
	public String getTodayAverageSubheading() {
		return todayAverageSubheading;
	}

	public void setTodayAverageSubheading(String todayAverageSubheading) {
		this.todayAverageSubheading = todayAverageSubheading;
	}

	@XmlElement(name="onboarded_heading", type=String.class) 
	@JsonProperty("onboarded_heading")
	public String getOnboardedHeading() {
		return onboardedHeading;
	}

	public void setOnboardedHeading(String onboardedHeading) {
		this.onboardedHeading = onboardedHeading;
	}

	@XmlElement(name="onboarded_count") 
	@JsonProperty("onboarded_count")
	public int getOnboardedCount() {
		return onboardedCount;
	}

	public void setOnboardedCount(int onboardedCount) {
		this.onboardedCount = onboardedCount;
	}

	@XmlElement(name="onboarded_subheading", type=String.class) 
	@JsonProperty("onboarded_subheading")
	public String getOnboardedSubheading() {
		return onboardedSubheading;
	}

	public void setOnboardedSubheading(String onboardedSubheading) {
		this.onboardedSubheading = onboardedSubheading;
	}

	@XmlElement(name="signups_heading", type=String.class) 
	@JsonProperty("signups_heading")
	public String getSignupsHeading() {
		return signupsHeading;
	}

	public void setSignupsHeading(String signupsHeading) {
		this.signupsHeading = signupsHeading;
	}

	@XmlElement(name="signups_count") 
	@JsonProperty("signups_count")
	public int getSignupsCount() {
		return signupsCount;
	}

	public void setSignupsCount(int signupsCount) {
		this.signupsCount = signupsCount;
	}

	@XmlElement(name="signups_subheading", type=String.class) 
	@JsonProperty("signups_subheading")
	public String getSignupsSubheading() {
		return signupsSubheading;
	}

	public void setSignupsSubheading(String signupsSubheading) {
		this.signupsSubheading = signupsSubheading;
	}

	@Override
	public String toString() {
		return "Statistics [id=" + id + ", todayHeading=" 
			+ todayHeading + ", todayCount=" + todayCount
			+ ", todayAverage=" + todayAverage 
			+ ", todayAverageSubheading=" + todayAverageSubheading
			+ ", onboardingHeading=" + onboardedHeading 
			+ ", onboardedCount=" + onboardedCount 
			+ ", onboardedSubheading=" + onboardedSubheading 
			+ ", signupsHeading=" + signupsHeading
			+ ", signupsCount=" + signupsCount 
			+ ", signupsSubheading=" + signupsSubheading + "]";
	}
}