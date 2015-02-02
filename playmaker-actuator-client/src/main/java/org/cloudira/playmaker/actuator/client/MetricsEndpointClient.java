package org.cloudira.playmaker.actuator.client;

import java.util.Map;

public class MetricsEndpointClient extends ActuatorEndpointClient<Map<String, Object>> {

	public MetricsEndpointClient(String managementUrl) {
		super(managementUrl);
	}
	
	@Override
	public String getEndpointPath() {
		return "metrics";
	}
	
	public String getValue(String name) {
		return invoke("{name}", String.class, name);
	}

}
