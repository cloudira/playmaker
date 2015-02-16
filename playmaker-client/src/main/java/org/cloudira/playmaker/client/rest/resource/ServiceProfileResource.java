package org.cloudira.playmaker.client.rest.resource;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class ServiceProfileResource {

	private String name;
	private Map<String, String> properties = new HashMap<>();
	
}
