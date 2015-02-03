package org.cloudira.playmaker.actuator.client;

import java.util.List;

import org.cloudira.playmaker.actuator.client.resource.ContextBeansResource;

public class BeansEndpointClient extends ActuatorEndpointClient<List<ContextBeansResource>> {

	public BeansEndpointClient(String managementUrl) {
		super(managementUrl);
	}
	
	@Override
	public String getEndpointPath() {
		return "beans";
	}

}
