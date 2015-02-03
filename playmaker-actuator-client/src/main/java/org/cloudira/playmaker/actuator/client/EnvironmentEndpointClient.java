package org.cloudira.playmaker.actuator.client;

import java.util.Collections;
import java.util.Map;

import org.cloudira.playmaker.actuator.client.resource.Environment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class EnvironmentEndpointClient extends ActuatorEndpointClient<Environment> {
	
	private static ParameterizedTypeReference<Map<String, String>> MAP_REFERENCE = new ParameterizedTypeReference<Map<String,String>>() {};
	
	@Override
	public String getEndpointPath() {
		return "env";
	}
	
	public String getValue(String managementUrl, String name) {
		return invoke(managementUrl, "{name}", String.class, name);
	}
	
	public Map<String, String> set(String managementUrl, Map<String, String> parameters) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.setAll(parameters);
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(multiValueMap, requestHeaders);
		return exchange(getUrl(managementUrl), HttpMethod.POST, requestEntity, MAP_REFERENCE, Collections.emptyMap()).getBody();
	}
	
	public Map<String, String> reset(String managementUrl) {
		return exchange(getUrl(managementUrl), HttpMethod.POST, null, MAP_REFERENCE, Collections.emptyMap()).getBody();
	}

}
