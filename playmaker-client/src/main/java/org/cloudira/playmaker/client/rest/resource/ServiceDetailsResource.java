package org.cloudira.playmaker.client.rest.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ServiceDetailsResource {

	private long id;
	private String name;
	private String description;
	private Map<String, String> properties = new HashMap<>();
	private List<ServiceProfileResource> profiles = new ArrayList<>();
	private List<InstanceResource> instances = new ArrayList<>();
	
}
