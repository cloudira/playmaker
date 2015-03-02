package org.cloudira.playmaker.client.rest.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceDetailsResource {

	private long id;
	private String name;
	private String description;
	private Map<String, String> properties = new HashMap<>();
	private List<ServiceProfileResource> profiles = new ArrayList<>();
	private List<InstanceResource> instances = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public List<ServiceProfileResource> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<ServiceProfileResource> profiles) {
		this.profiles = profiles;
	}

	public List<InstanceResource> getInstances() {
		return instances;
	}

	public void setInstances(List<InstanceResource> instances) {
		this.instances = instances;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServiceDetailsResource [id=");
		builder.append(id);
		builder.append(", ");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (description != null) {
			builder.append("description=");
			builder.append(description);
			builder.append(", ");
		}
		if (properties != null) {
			builder.append("properties=");
			builder.append(properties);
			builder.append(", ");
		}
		if (profiles != null) {
			builder.append("profiles=");
			builder.append(profiles);
			builder.append(", ");
		}
		if (instances != null) {
			builder.append("instances=");
			builder.append(instances);
		}
		builder.append("]");
		return builder.toString();
	}

}
