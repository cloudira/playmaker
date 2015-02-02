package org.cloudira.playmaker.actuator.client.resource;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class MappingsResource {
	
	private Map<String, MappingResource> mappings = new LinkedHashMap<String, MappingsResource.MappingResource>();
	
	@JsonAnyGetter
	public Map<String, MappingResource> getMappings() {
		return mappings;
	}
	
	@JsonAnySetter
	public void setMapping(String key, MappingResource value) {
		getMappings().put(key, value);
	}
	
	public static class MappingResource {
		
		private String bean;
		private String method;
		
		public String getBean() {
			return bean;
		}
		
		public void setBean(String bean) {
			this.bean = bean;
		}
		
		public String getMethod() {
			return method;
		}
		
		public void setMethod(String method) {
			this.method = method;
		}

		@Override
		public String toString() {
			return "MappingResource [bean=" + bean + ", method=" + method + "]";
		}
		
	}

	@Override
	public String toString() {
		return "MappingsResource [mappings=" + mappings + "]";
	}
	
}
