package org.cloudira.playmaker.actuator.client;

import java.util.Map;

import org.springframework.http.HttpMethod;

public class ShutdownEndpointClient extends ActuatorEndpointClient<Map<String, Object>>{

	public ShutdownEndpointClient(String managementUrl) {
		super(managementUrl);
	}
	
	@Override
	public String getEndpointPath() {
		return "shutdown";
	}
	
	@Override
	public HttpMethod getHttpMethod() {
		return HttpMethod.POST;
	}

}
