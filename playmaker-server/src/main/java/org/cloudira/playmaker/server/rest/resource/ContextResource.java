package org.cloudira.playmaker.server.rest.resource;

import java.util.List;

public class ContextResource {

	private String name;
	
	private boolean open;
	
	private List<BeanResource> beans;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public void setOpen(boolean open) {
		this.open = open;
	}

	public List<BeanResource> getBeans() {
		return beans;
	}

	public void setBeans(List<BeanResource> beans) {
		this.beans = beans;
	}
	
}
