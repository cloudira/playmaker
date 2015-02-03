package org.cloudira.playmaker.actuator.client;

import java.util.Map;

public class InfoEndpointClient extends ActuatorEndpointClient<Map<String, Object>> {
	
	@Override
	public String getEndpointPath() {
		return "info";
	}

}
