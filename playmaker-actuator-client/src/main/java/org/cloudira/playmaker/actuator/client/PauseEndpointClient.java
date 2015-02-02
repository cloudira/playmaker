package org.cloudira.playmaker.actuator.client;

import org.springframework.http.HttpMethod;

public class PauseEndpointClient extends ActuatorEndpointClient<Boolean>{

	public PauseEndpointClient(String managementUrl) {
		super(managementUrl);
	}
	
	@Override
	public String getEndpointPath() {
		return "pause";
	}
	
	@Override
	public HttpMethod getHttpMethod() {
		return HttpMethod.POST;
	}

}
