package org.cloudira.playmaker.actuator.client.resource;

import java.util.Date;
import java.util.Map;

public final class Trace {

	private Date timestamp;

	private Map<String, Object> info;

	public Trace() {
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Map<String, Object> getInfo() {
		return this.info;
	}

	public void setInfo(Map<String, Object> info) {
		this.info = info;
	}
	
	@Override
	public String toString() {
		return "Trace [timestamp=" + timestamp + ", info=" + info + "]";
	}

}
