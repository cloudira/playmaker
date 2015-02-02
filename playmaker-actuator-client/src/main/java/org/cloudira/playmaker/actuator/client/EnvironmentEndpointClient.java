package org.cloudira.playmaker.actuator.client;

import java.util.Collections;
import java.util.Map;

import org.cloudira.playmaker.actuator.client.resource.EnvironmentResource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class EnvironmentEndpointClient extends ActuatorEndpointClient<EnvironmentResource> {
	
	private static ParameterizedTypeReference<Map<String, String>> MAP_REFERENCE = new ParameterizedTypeReference<Map<String,String>>() {};

	public EnvironmentEndpointClient(String managementUrl) {
		super(managementUrl);
	}
	
	@Override
	public String getEndpointPath() {
		return "env";
	}
	
	public String getValue(String name) {
		return invoke("{name}", String.class, name);
	}
	
	public Map<String, String> set(Map<String, String> parameters) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.setAll(parameters);
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(multiValueMap, requestHeaders);
		return exchange(getUrl(), HttpMethod.POST, requestEntity, MAP_REFERENCE, Collections.emptyMap()).getBody();
	}
	
	public Map<String, String> reset() {
		return exchange(getUrl(), HttpMethod.POST, null, MAP_REFERENCE, Collections.emptyMap()).getBody();
	}

}
