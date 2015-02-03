package org.cloudira.playmaker.actuator.client;

import java.util.Map;

public class MetricsEndpointClient extends ActuatorEndpointClient<Map<String, Object>> {
	
	@Override
	public String getEndpointPath() {
		return "metrics";
	}
	
	public String getValue(String managementUrl, String name) {
		return invoke(managementUrl, "{name}", String.class, name);
	}

}
