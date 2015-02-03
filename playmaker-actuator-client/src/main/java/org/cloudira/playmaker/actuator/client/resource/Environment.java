package org.cloudira.playmaker.actuator.client.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;

public class Environment {

	private String[] profiles;
	
	private List<PropertySource> propertySources = new ArrayList<>();
	
	public String[] getProfiles() {
		return profiles;
	}
	
	public void setProfiles(String[] profiles) {
		this.profiles = profiles;
	}

	public List<PropertySource> getPropertySources() {
		return propertySources;
	}
	
	public void setPropertySources(List<PropertySource> propertySources) {
		this.propertySources = propertySources;
	}
	
	@JsonAnySetter
	public void addPropertySource(String key, Map<String, String> value) {
		getPropertySources().add(new PropertySource(key, value));
	}
	
	public static class PropertySource {
		
		private String name;
		
		private Map<String, String> properties;
		
		public PropertySource() {
			
		}
		
		public PropertySource(String name, Map<String, String> properties) {
			this.name = name;
			this.properties = properties;
		}
		
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
			return "PropertySource [name=" + name + ", properties=" + properties + "]";
		}
		
	}

	@Override
	public String toString() {
		return "Environment [profiles=" + Arrays.toString(profiles) + ", propertySources=" + propertySources + "]";
	}
	
}
