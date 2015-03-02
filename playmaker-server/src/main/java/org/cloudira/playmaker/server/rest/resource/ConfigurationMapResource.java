package org.cloudira.playmaker.server.rest.resource;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ConfigurationMapResource extends ConfigurationItemResource {
	
	@JsonInclude(Include.NON_EMPTY)
	private List<ConfigurationMapResource> maps = new ArrayList<ConfigurationMapResource>();
	
	@JsonInclude(Include.NON_EMPTY)
	private List<ConfigurationPropertyResource> properties = new ArrayList<ConfigurationPropertyResource>();
	
	public List<ConfigurationMapResource> getMaps() {
		return maps;
	}

	public void setMaps(List<ConfigurationMapResource> maps) {
		this.maps = maps;
	}

	public List<ConfigurationPropertyResource> getProperties() {
		return properties;
	}

	public void setProperties(List<ConfigurationPropertyResource> properties) {
		this.properties = properties;
	}
	
}
