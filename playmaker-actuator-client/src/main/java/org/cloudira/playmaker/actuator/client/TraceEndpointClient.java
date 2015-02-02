package org.cloudira.playmaker.actuator.client;

import java.util.List;

import org.cloudira.playmaker.actuator.client.resource.TraceResource;

public class TraceEndpointClient extends ActuatorEndpointClient<List<TraceResource>> {
	
	public TraceEndpointClient(String managementUrl) {
		super(managementUrl);
	}
	
	@Override
	public String getEndpointPath() {
		return "trace";
	}

}
