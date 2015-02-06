package org.cloudira.playmaker.server.rest.resource;

import java.util.Map;

public class MetricsResource {

	private Map<String, Integer> counters;
	
	private Map<String, Double> gauges;

	public Map<String, Integer> getCounters() {
		return counters;
	}

	public void setCounters(Map<String, Integer> counters) {
		this.counters = counters;
	}

	public Map<String, Double> getGauges() {
		return gauges;
	}

	public void setGauges(Map<String, Double> gauges) {
		this.gauges = gauges;
	}
	
}
