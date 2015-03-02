package org.cloudira.playmaker.client.rest;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rest.client")
public class RestDiscoveryClientConfiguration {

	private String uri;
	private int registrationInterval = 30000;
	
	public String getUri() {
		return uri;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public int getRegistrationInterval() {
		return registrationInterval;
	}
	
	public void setRegistrationInterval(int registrationInterval) {
		this.registrationInterval = registrationInterval;
	}
	
}
