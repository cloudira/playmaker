package org.cloudira.playmaker.actuator.client;

import java.util.Map;

public class ConfigurationPropertiesEndpointClient extends ActuatorEndpointClient<Map<String, Object>> {

	public ConfigurationPropertiesEndpointClient(String managementUrl) {
		super(managementUrl);
	}
	
	@Override
	public String getEndpointPath() {
		return "configprops";
	}

}
