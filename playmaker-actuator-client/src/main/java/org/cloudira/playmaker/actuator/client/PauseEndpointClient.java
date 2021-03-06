package org.cloudira.playmaker.actuator.client;

import org.springframework.http.HttpMethod;

public class PauseEndpointClient extends ActuatorEndpointClient<Boolean> {
	
	@Override
	public String getEndpointPath() {
		return "pause";
	}
	
	@Override
	public HttpMethod getHttpMethod() {
		return HttpMethod.POST;
	}

}
