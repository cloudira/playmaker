package org.cloudira.playmaker.actuator.client;

import java.util.List;

import org.cloudira.playmaker.actuator.client.resource.ContextBeans;

public class BeansEndpointClient extends ActuatorEndpointClient<List<ContextBeans>> {
	
	@Override
	public String getEndpointPath() {
		return "beans";
	}

}
