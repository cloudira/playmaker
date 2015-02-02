package org.cloudira.playmaker.actuator.client.resource;

import java.util.List;
import java.util.Map;

public class AutoConfigurationReport {

	private Map<String, List<MessageAndCondition>> positiveMatches;

	private Map<String, List<MessageAndCondition>> negativeMatches;

	private AutoConfigurationReport parent;

	public Map<String, List<MessageAndCondition>> getPositiveMatches() {
		return this.positiveMatches;
	}

	public void setPositiveMatches(Map<String, List<MessageAndCondition>> positiveMatches) {
		this.positiveMatches = positiveMatches;
	}

	public Map<String, List<MessageAndCondition>> getNegativeMatches() {
		return this.negativeMatches;
	}

	public void setNegativeMatches(Map<String, List<MessageAndCondition>> negativeMatches) {
		this.negativeMatches = negativeMatches;
	}

	public AutoConfigurationReport getParent() {
		return this.parent;
	}

	public void setParent(AutoConfigurationReport parent) {
		this.parent = parent;
	}

	public static class MessageAndCondition {

		private String condition;

		private String message;

		public String getCondition() {
			return this.condition;
		}

		public void setCondition(String condition) {
			this.condition = condition;
		}

		public String getMessage() {
			return this.message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		@Override
		public String toString() {
			return "MessageAndCondition [condition=" + condition + ", message=" + message + "]";
		}

	}

	@Override
	public String toString() {
		return "Report [positiveMatches=" + positiveMatches + ", negativeMatches=" + negativeMatches + ", parent=" + parent + "]";
	}

}
