package org.cloudira.playmaker.server.rest.resource;

import java.util.HashMap;
import java.util.Map;

public class ServiceProfileResource {

	private String name;
	
	private Map<String, String> properties = new HashMap<String, String>();

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
