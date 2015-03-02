package org.cloudira.playmaker.server.rest.resource;

public class ConfigurationPropertyResource extends ConfigurationItemResource {
	
	private String value;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
