package org.cloudira.playmaker.actuator.client.resource;

import java.util.List;

public class ContextBeansResource {

	private String context;
	private String parent;
	private List<BeanResource> beans;

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public List<BeanResource> getBeans() {
		return beans;
	}

	public void setBeans(List<BeanResource> beans) {
		this.beans = beans;
	}

	public static class BeanResource {
		private String bean;
		private String scope;
		private String type;
		private List<String> dependencies;

		public String getBean() {
			return bean;
		}

		public void setBean(String bean) {
			this.bean = bean;
		}

		public String getScope() {
			return scope;
		}

		public void setScope(String scope) {
			this.scope = scope;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public List<String> getDependencies() {
			return dependencies;
		}

		public void setDependencies(List<String> dependencies) {
			this.dependencies = dependencies;
		}

		@Override
		public String toString() {
			return "BeanResource [bean=" + bean + ", scope=" + scope + ", type=" + type + ", dependencies=" + dependencies + "]";
		}
	}

	@Override
	public String toString() {
		return "ContextBeansResource [context=" + context + ", parent=" + parent + ", beans=" + beans + "]";
	}

}
