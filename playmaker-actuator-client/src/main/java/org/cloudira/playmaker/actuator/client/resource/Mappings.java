package org.cloudira.playmaker.actuator.client.resource;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonAnySetter;

public class Mappings {
	
	private static Pattern pathsPattern = Pattern.compile("\\{\\[([^\\];]*)");
	private static Pattern detailsPattern = Pattern.compile("(\\w+)=\\[([^\\];]*)");
	
	private List<RequestMap> mappings = new LinkedList<>();
	
	public List<RequestMap> getMappings() {
		return mappings;
	}
	
	@JsonAnySetter
	public void setMapping(String key, Mapping value) {
		getMappings().add(new RequestMap(key, value.getBean(), value.getMethod()));
	}
	
	static class RequestMap {
		
		private String value;
		private String[] paths;
		private String[] methods;
		private String[] params;
		private String[] headers;
		private String[] consumes;
		private String[] produces;
		private String[] custom;
		
		private String bean;
		private String method;
		
		public RequestMap(String value, String bean, String method) {
			this.value = value;
			parse();
			this.bean = bean;
			this.method = method;
		}
		
		protected void parse() {
			Matcher matcher = pathsPattern.matcher(value);
			if (matcher.find()) {
				setPaths(StringUtils.delimitedListToStringArray(matcher.group(1), " || "));
			} else {
				setPaths(StringUtils.delimitedListToStringArray(value, " || "));
			}
			
			matcher = detailsPattern.matcher(value);
			while (matcher.find()) {
				switch (matcher.group(1)) {
				case "methods":
					setMethods(StringUtils.delimitedListToStringArray(matcher.group(2), " || "));
					break;
				case "params":
					setParams(StringUtils.delimitedListToStringArray(matcher.group(2), " && "));
					break;
				case "headers":
					setHeaders(StringUtils.delimitedListToStringArray(matcher.group(2), " && "));
					break;
				case "consumes":
					setConsumes(StringUtils.delimitedListToStringArray(matcher.group(2), " || "));
					break;
				case "produces":
					setProduces(StringUtils.delimitedListToStringArray(matcher.group(2), " || "));
					break;
				case "custom":
					setCustom(StringUtils.delimitedListToStringArray(matcher.group(2), " || "));
					break;
				}
			}
		}
		
		public String[] getPaths() {
			return paths;
		}
		
		public void setPaths(String[] paths) {
			this.paths = paths;
		}
		
		public String[] getMethods() {
			return methods;
		}
		
		public void setMethods(String[] methods) {
			this.methods = methods;
		}
		
		public String[] getParams() {
			return params;
		}
		
		public void setParams(String[] params) {
			this.params = params;
		}
		
		public String[] getHeaders() {
			return headers;
		}
		
		public void setHeaders(String[] headers) {
			this.headers = headers;
		}
		
		public String[] getConsumes() {
			return consumes;
		}
		
		public void setConsumes(String[] consumes) {
			this.consumes = consumes;
		}
		
		public String[] getProduces() {
			return produces;
		}
		
		public void setProduces(String[] produces) {
			this.produces = produces;
		}
		
		public String[] getCustom() {
			return custom;
		}
		
		public void setCustom(String[] custom) {
			this.custom = custom;
		}
		
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
			StringBuilder builder = new StringBuilder();
			builder.append("RequestMap [");
			if (value != null) {
				builder.append("value=");
				builder.append(value);
				builder.append(", ");
			}
			if (paths != null) {
				builder.append("paths=");
				builder.append(Arrays.toString(paths));
				builder.append(", ");
			}
			if (methods != null) {
				builder.append("methods=");
				builder.append(Arrays.toString(methods));
				builder.append(", ");
			}
			if (params != null) {
				builder.append("params=");
				builder.append(Arrays.toString(params));
				builder.append(", ");
			}
			if (headers != null) {
				builder.append("headers=");
				builder.append(Arrays.toString(headers));
				builder.append(", ");
			}
			if (consumes != null) {
				builder.append("consumes=");
				builder.append(Arrays.toString(consumes));
				builder.append(", ");
			}
			if (produces != null) {
				builder.append("produces=");
				builder.append(Arrays.toString(produces));
				builder.append(", ");
			}
			if (custom != null) {
				builder.append("custom=");
				builder.append(Arrays.toString(custom));
				builder.append(", ");
			}
			if (bean != null) {
				builder.append("bean=");
				builder.append(bean);
				builder.append(", ");
			}
			if (method != null) {
				builder.append("method=");
				builder.append(method);
			}
			builder.append("]");
			return builder.toString();
		}
		
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
	}

	@Override
	public String toString() {
		return "Mappings [mappings=" + mappings + "]";
	}
	
}
