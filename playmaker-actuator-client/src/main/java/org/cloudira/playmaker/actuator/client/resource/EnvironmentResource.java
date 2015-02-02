package org.cloudira.playmaker.actuator.client.resource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class EnvironmentResource {

	private String[] profiles;
	private Map<String, String> bootstrap;
	private Map<String, String> servletContextInitParams;
	private Map<String, String> systemProperties;
	private Map<String, String> systemEnvironment;
	
	private Map<String, Map<String, Object>> details = new HashMap<>();
	
	public String[] getProfiles() {
		return profiles;
	}
	
	public void setProfiles(String[] profiles) {
		this.profiles = profiles;
	}
	
	public Map<String, String> getBootstrap() {
		return bootstrap;
	}

	public void setBootstrap(Map<String, String> bootstrap) {
		this.bootstrap = bootstrap;
	}

	public Map<String, String> getServletContextInitParams() {
		return servletContextInitParams;
	}

	public void setServletContextInitParams(Map<String, String> servletContextInitParams) {
		this.servletContextInitParams = servletContextInitParams;
	}

	public Map<String, String> getSystemProperties() {
		return systemProperties;
	}

	public void setSystemProperties(Map<String, String> systemProperties) {
		this.systemProperties = systemProperties;
	}

	public Map<String, String> getSystemEnvironment() {
		return systemEnvironment;
	}

	public void setSystemEnvironment(Map<String, String> systemEnvironment) {
		this.systemEnvironment = systemEnvironment;
	}

	@JsonAnyGetter
	public Map<String, Map<String, Object>> getDetails() {
		return details;
	}
	
	@JsonAnySetter
	public void setDetail(String key, Map<String, Object> value) {
		getDetails().put(key, value);
	}

	@Override
	public String toString() {
		return "EnvironmentResource [profiles=" + Arrays.toString(profiles) + ", bootstrap=" + bootstrap + ", servletContextInitParams=" + servletContextInitParams + ", systemProperties=" + systemProperties + ", systemEnvironment=" + systemEnvironment + ", details=" + details + "]";
	}
	
}
