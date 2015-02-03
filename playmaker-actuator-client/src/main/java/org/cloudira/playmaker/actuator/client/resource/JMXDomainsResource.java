package org.cloudira.playmaker.actuator.client.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class JMXDomainsResource implements JMXResource {

	private Map<String, JMXDomainResource> domains = new HashMap<>();

	@JsonAnyGetter
	public Map<String, JMXDomainResource> getDomains() {
		return domains;
	}

	@JsonAnySetter
	public void setDomain(String name, JMXDomainResource value) {
		getDomains().put(name, value);
	}
	
	public static class JMXDomainResource implements JMXResource {
		private Map<String, JMXDomainTypeResource> types = new HashMap<>();
		
		@JsonAnyGetter
		public Map<String, JMXDomainTypeResource> getTypes() {
			return types;
		}

		@JsonAnySetter
		public void setType(String name, JMXDomainTypeResource value) {
			getTypes().put(name, value);
		}
		
		@Override
		public String toString() {
			return types.toString();
		}
	}

	public static class JMXDomainTypeResource implements JMXResource {
		private String desc;

		private Map<String, List<JMXOperationResource>> op;
		private Map<String, JMXAttributeResource> attr;

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public Map<String, List<JMXOperationResource>> getOp() {
			return op;
		}

		public void setOp(Map<String, List<JMXOperationResource>> op) {
			this.op = op;
		}

		public Map<String, JMXAttributeResource> getAttr() {
			return attr;
		}

		public void setAttr(Map<String, JMXAttributeResource> attr) {
			this.attr = attr;
		}

		@Override
		public String toString() {
			return "JMXDomainTypeResource [desc=" + desc + ", op=" + op + ", attr=" + attr + "]";
		}
	}

	public static class JMXOperationResource {
		private String ret;
		private String desc;
		private List<JMXOperationArgumentResource> args;

		public String getRet() {
			return ret;
		}

		public void setRet(String ret) {
			this.ret = ret;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public List<JMXOperationArgumentResource> getArgs() {
			return args;
		}

		public void setArgs(List<JMXOperationArgumentResource> args) {
			this.args = args;
		}

		@Override
		public String toString() {
			return "JMXOperationResource [ret=" + ret + ", desc=" + desc + ", args=" + args + "]";
		}
	}

	public static class JMXAttributeResource {
		private String desc;
		private String type;
		private boolean rw;

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public boolean isRw() {
			return rw;
		}

		public void setRw(boolean rw) {
			this.rw = rw;
		}

		@Override
		public String toString() {
			return "JMXAttributeResource [desc=" + desc + ", type=" + type + ", rw=" + rw + "]";
		}
	}

	public static class JMXOperationArgumentResource {
		private String desc;
		private String name;
		private String type;

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		@Override
		public String toString() {
			return "JMXOperationArgumentResource [desc=" + desc + ", name=" + name + ", type=" + type + "]";
		}
	}

	@Override
	public String toString() {
		return "JMXDomainsResource [domains=" + domains + "]";
	}

}
