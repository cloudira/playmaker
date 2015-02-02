package org.cloudira.playmaker.actuator.client.resource;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public final class HealthResource {

	private String status;

	private Map<String, Object> details = new HashMap<>();
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonAnyGetter
	public Map<String, Object> getDetails() {
		return this.details;
	}
	
	@JsonAnySetter
	public void setDetail(String key, Object value) {
		getDetails().put(key, value);
	}

	@Override
	public String toString() {
		return getStatus() + " " + getDetails();
	}

}
