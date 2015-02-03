package org.cloudira.playmaker.actuator.client.resource;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class Mappings {
	
	private Map<String, Mapping> mappings = new LinkedHashMap<>();
	
	@JsonAnyGetter
	public Map<String, Mapping> getMappings() {
		return mappings;
	}
	
	@JsonAnySetter
	public void setMapping(String key, Mapping value) {
		getMappings().put(key, value);
	}
	
	public static class Mapping {
		
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
			return "Mapping [bean=" + bean + ", method=" + method + "]";
		}
		
	}

	@Override
	public String toString() {
		return "Mappings [mappings=" + mappings + "]";
	}
	
}
