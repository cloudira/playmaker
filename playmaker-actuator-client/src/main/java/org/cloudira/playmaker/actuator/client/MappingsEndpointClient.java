package org.cloudira.playmaker.actuator.client;

import org.cloudira.playmaker.actuator.client.resource.Mappings;

public class MappingsEndpointClient extends ActuatorEndpointClient<Mappings> {
	
	@Override
	public String getEndpointPath() {
		return "mappings";
	}

}
