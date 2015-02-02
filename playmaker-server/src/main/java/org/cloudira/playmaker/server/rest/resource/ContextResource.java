package org.cloudira.playmaker.server.rest.resource;

import java.util.List;

public class ContextResource {

	private String name;
	
	private List<ContextResource> contexts;
	
	private List<BeanResource> beans;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ContextResource> getContexts() {
		return contexts;
	}

	public void setContexts(List<ContextResource> contexts) {
		this.contexts = contexts;
	}

	public List<BeanResource> getBeans() {
		return beans;
	}

	public void setBeans(List<BeanResource> beans) {
		this.beans = beans;
	}
	
}
