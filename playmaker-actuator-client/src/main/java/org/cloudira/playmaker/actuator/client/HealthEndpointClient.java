package org.cloudira.playmaker.actuator.client;

import org.cloudira.playmaker.actuator.client.resource.Health;

public class HealthEndpointClient extends ActuatorEndpointClient<Health> {
	
	@Override
	public String getEndpointPath() {
		return "health";
	}

}
