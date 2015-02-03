package org.cloudira.playmaker.actuator.client;

import java.util.List;

import org.cloudira.playmaker.actuator.client.resource.ThreadInfo;

public class DumpEndpointClient extends ActuatorEndpointClient<List<ThreadInfo>> {
	
	@Override
	public String getEndpointPath() {
		return "dump";
	}

}
