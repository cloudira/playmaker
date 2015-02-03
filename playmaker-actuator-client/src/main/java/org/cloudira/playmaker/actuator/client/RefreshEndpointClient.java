package org.cloudira.playmaker.actuator.client;

import java.util.List;

import org.springframework.http.HttpMethod;

public class RefreshEndpointClient extends ActuatorEndpointClient<List<String>> {
	
	@Override
	public String getEndpointPath() {
		return "refresh";
	}
	
	@Override
	public HttpMethod getHttpMethod() {
		return HttpMethod.POST;
	}
	
}
