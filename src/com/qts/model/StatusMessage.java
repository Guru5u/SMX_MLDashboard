package com.qts.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

//@XmlRootElement(name = "statusmessage")
@JsonPropertyOrder({ "status_code", "message" })
public class StatusMessage {

	private Integer status;
	private String message;
	
	// private constructor
	public StatusMessage() {
	}

	//@XmlElement(name = "status_code")
	@JsonProperty(value = "status_code")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	//@XmlElement(name = "message")
	@JsonProperty(value = "message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
