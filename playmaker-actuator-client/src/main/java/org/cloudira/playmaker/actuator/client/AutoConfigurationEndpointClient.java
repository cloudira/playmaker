package org.cloudira.playmaker.actuator.client;

import org.cloudira.playmaker.actuator.client.resource.AutoConfigurationReport;

public class AutoConfigurationEndpointClient extends ActuatorEndpointClient<AutoConfigurationReport> {

	public AutoConfigurationEndpointClient(String managementUrl) {
		super(managementUrl);
	}
	
	@Override
	public String getEndpointPath() {
		return "autoconfig";
	}

}
