package org.cloudira.playmaker.actuator.client;

import org.cloudira.playmaker.actuator.client.resource.HealthResource;

public class HealthEndpointClient extends ActuatorEndpointClient<HealthResource> {
	
	@Override
	public String getEndpointPath() {
		return "health";
	}

}
