package org.cloudira.playmaker.actuator.client;

import org.cloudira.playmaker.actuator.client.resource.MappingsResource;

public class MappingsEndpointClient extends ActuatorEndpointClient<MappingsResource> {
	
	@Override
	public String getEndpointPath() {
		return "mappings";
	}

}
