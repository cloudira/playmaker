package org.cloudira.playmaker.server.rest.resource;

import java.util.Map;

public class EnvironmentResource {

	private String name;
	
	private Map<String, String> properties;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	
}
