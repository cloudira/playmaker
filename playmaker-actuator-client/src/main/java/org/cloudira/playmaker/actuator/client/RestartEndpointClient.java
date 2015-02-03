package org.cloudira.playmaker.actuator.client;

import java.util.Map;

import org.springframework.http.HttpMethod;

public class RestartEndpointClient extends ActuatorEndpointClient<Map<String, Object>> {
	
	@Override
	public String getEndpointPath() {
		return "restart";
	}
	
	@Override
	public HttpMethod getHttpMethod() {
		return HttpMethod.POST;
	}

}
