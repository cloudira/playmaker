package org.cloudira.playmaker.actuator.client;

import org.cloudira.playmaker.actuator.client.resource.HealthResource;

public class HealthEndpointClient extends ActuatorEndpointClient<HealthResource> {

	public HealthEndpointClient(String managementUrl) {
		super(managementUrl);
	}
	
	@Override
	public String getEndpointPath() {
		return "health";
	}

}
