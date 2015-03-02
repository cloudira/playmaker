package org.cloudira.playmaker.server.rest.resource;

import java.util.List;

public class ConfigurationResource {
	
	private String profile;

	private List<IdNameResource> path;
	
	private ConfigurationItemResource item;

	public String getProfile() {
		return profile;
	}
	
	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	public List<IdNameResource> getPath() {
		return path;
	}

	public void setPath(List<IdNameResource> path) {
		this.path = path;
	}

	public ConfigurationItemResource getItem() {
		return item;
	}

	public void setItem(ConfigurationItemResource item) {
		this.item = item;
	}
	
}
