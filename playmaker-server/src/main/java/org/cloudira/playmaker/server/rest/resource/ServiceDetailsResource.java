package org.cloudira.playmaker.server.rest.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceDetailsResource {

	private long id;
	
	private String name;
	
	private String description;
	
	private Map<String, String> properties = new HashMap<String, String>();
	
	private List<ServiceProfileResource> profiles = new ArrayList<ServiceProfileResource>();
	
	private List<InstanceResource> instances = new ArrayList<InstanceResource>();

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
	
}
