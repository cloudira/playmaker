package org.cloudira.playmaker.actuator.client;

import java.util.Map;

public class InfoEndpointClient extends ActuatorEndpointClient<Map<String, Object>> {

	public InfoEndpointClient(String managementUrl) {
		super(managementUrl);
	}
	
	@Override
	public String getEndpointPath() {
		return "info";
	}

}
