package org.cloudira.playmaker.actuator.client.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class JMXDomains implements JMX {

	private Map<String, JMXDomain> domains = new HashMap<>();

	@JsonAnyGetter
	public Map<String, JMXDomain> getDomains() {
		return domains;
	}

	@JsonAnySetter
	public void setDomain(String name, JMXDomain value) {
		getDomains().put(name, value);
	}
	
	public static class JMXDomain implements JMX {
		
		private Map<String, JMXDomainType> types = new HashMap<>();
		
		@JsonAnyGetter
		public Map<String, JMXDomainType> getTypes() {
			return types;
		}

		@JsonAnySetter
		public void setType(String name, JMXDomainType value) {
			getTypes().put(name, value);
		}
		
		@Override
		public String toString() {
			return types.toString();
		}
	}

	public static class JMXDomainType implements JMX {
		
		private String desc;

		private Map<String, List<JMXOperation>> op;
		private Map<String, JMXAttribute> attr;

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public Map<String, List<JMXOperation>> getOp() {
			return op;
		}

		public void setOp(Map<String, List<JMXOperation>> op) {
			this.op = op;
		}

		public Map<String, JMXAttribute> getAttr() {
			return attr;
		}

		public void setAttr(Map<String, JMXAttribute> attr) {
			this.attr = attr;
		}

		@Override
		public String toString() {
			return "JMXDomainType [desc=" + desc + ", op=" + op + ", attr=" + attr + "]";
		}
	}

	public static class JMXOperation {
		
		private String ret;
		private String desc;
		private List<JMXOperationArgument> args;

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

		public List<JMXOperationArgument> getArgs() {
			return args;
		}

		public void setArgs(List<JMXOperationArgument> args) {
			this.args = args;
		}

		@Override
		public String toString() {
			return "JMXOperation [ret=" + ret + ", desc=" + desc + ", args=" + args + "]";
		}
	}

	public static class JMXAttribute {
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
			return "JMXAttribute [desc=" + desc + ", type=" + type + ", rw=" + rw + "]";
		}
	}

	public static class JMXOperationArgument {
		
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
			return "JMXOperationArgument [desc=" + desc + ", name=" + name + ", type=" + type + "]";
		}
	}

	@Override
	public String toString() {
		return "JMXDomains [domains=" + domains + "]";
	}

}
