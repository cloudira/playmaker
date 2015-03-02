package org.cloudira.playmaker.server.rest.resource;

import java.util.List;

public class ServiceConfigResource {

	private ServiceResource service;
	
	private List<ProfileResource> profiles;

	public ServiceResource getService() {
		return service;
	}

	public void setService(ServiceResource service) {
		this.service = service;
	}

	public List<ProfileResource> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<ProfileResource> profiles) {
		this.profiles = profiles;
	}
	
}
