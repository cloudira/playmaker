package org.cloudira.playmaker.actuator.client;

import java.util.List;

import org.cloudira.playmaker.actuator.client.resource.ContextBeansResource;

public class BeansEndpointClient extends ActuatorEndpointClient<List<ContextBeansResource>> {
	
	@Override
	public String getEndpointPath() {
		return "beans";
	}

}
