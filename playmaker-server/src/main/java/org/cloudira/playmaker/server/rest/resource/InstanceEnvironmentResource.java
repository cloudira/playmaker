package org.cloudira.playmaker.server.rest.resource;

import java.util.Map;

public class InstanceEnvironmentResource {

	private String name;
	
	private boolean open;
	
	private Map<String, String> properties;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public void setOpen(boolean open) {
		this.open = open;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	
}
