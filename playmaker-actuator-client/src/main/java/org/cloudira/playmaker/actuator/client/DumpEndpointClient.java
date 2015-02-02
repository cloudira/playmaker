package org.cloudira.playmaker.actuator.client;

import java.util.List;

import org.cloudira.playmaker.actuator.client.resource.ThreadInfoResource;

public class DumpEndpointClient extends ActuatorEndpointClient<List<ThreadInfoResource>> {
	
	public DumpEndpointClient(String managementUrl) {
		super(managementUrl);
	}
	
	@Override
	public String getEndpointPath() {
		return "dump";
	}

}
