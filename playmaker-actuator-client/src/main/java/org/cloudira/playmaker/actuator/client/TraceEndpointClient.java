package org.cloudira.playmaker.actuator.client;

import java.util.List;

import org.cloudira.playmaker.actuator.client.resource.Trace;

public class TraceEndpointClient extends ActuatorEndpointClient<List<Trace>> {
	
	@Override
	public String getEndpointPath() {
		return "trace";
	}

}
