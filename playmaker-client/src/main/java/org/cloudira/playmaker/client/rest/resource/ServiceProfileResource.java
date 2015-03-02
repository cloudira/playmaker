package org.cloudira.playmaker.client.rest.resource;

import java.util.HashMap;
import java.util.Map;

public class ServiceProfileResource {

	private String name;
	private Map<String, String> properties = new HashMap<>();

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServiceProfileResource [");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (properties != null) {
			builder.append("properties=");
			builder.append(properties);
		}
		builder.append("]");
		return builder.toString();
	}

}
