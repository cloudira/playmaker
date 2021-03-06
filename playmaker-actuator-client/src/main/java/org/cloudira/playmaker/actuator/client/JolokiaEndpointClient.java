package org.cloudira.playmaker.actuator.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.management.MalformedObjectNameException;

import org.cloudira.playmaker.actuator.client.resource.JMXDomains;
import org.cloudira.playmaker.actuator.client.resource.JMXDomains.JMXDomain;
import org.cloudira.playmaker.actuator.client.resource.JMXDomains.JMXDomainType;
import org.cloudira.playmaker.actuator.client.resource.JMX;
import org.jolokia.client.J4pClient;
import org.jolokia.client.J4pClientBuilderFactory;
import org.jolokia.client.exception.J4pException;
import org.jolokia.client.request.J4pExecRequest;
import org.jolokia.client.request.J4pListRequest;
import org.jolokia.client.request.J4pReadRequest;
import org.jolokia.client.request.J4pResponse;
import org.json.simple.JSONObject;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JolokiaEndpointClient {
	
	public enum LoggerLevel {
		OFF, ERROR, WARN, INFO, DEBUG, TRACE, ALL
	}
	
	protected ObjectMapper objectMapper = new ObjectMapper();
	
	public JolokiaEndpointClient() {
		objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
	}
	
	public String getEndpointPath() {
		return "jolokia";
	}
	
	protected String getUrl(String managementUrl, String... pathSegments) {
		return UriComponentsBuilder.fromHttpUrl(managementUrl).pathSegment(getEndpointPath()).build().toString();
	}
	
	protected J4pClient getClient(String managementUrl) {
		return J4pClientBuilderFactory.url(getUrl(managementUrl)).build();
	}
	
	public JMX getList(String managementUrl, String... paths) throws J4pException {
		try {
			JSONObject result = ((JSONObject) getClient(managementUrl).execute(new J4pListRequest(Arrays.asList(paths))).getValue());
			if (paths.length == 0) {
				return objectMapper.readValue(result.toJSONString().getBytes(), JMXDomains.class);
			} else if (paths.length == 1) {
				return objectMapper.readValue(result.toJSONString().getBytes(), JMXDomain.class);
			} else if (paths.length == 2) {
				return objectMapper.readValue(result.toJSONString().getBytes(), JMXDomainType.class);
			}
			throw new IllegalArgumentException("Usupported paths size: " + paths.length + " -> " + Arrays.toString(paths));
		} catch (IOException e) {
			throw new J4pException(e.getMessage(), e);
		}
	}

	public List<String> getLoggerList(String managementUrl) throws J4pException, MalformedObjectNameException {
		J4pReadRequest request = new J4pReadRequest("ch.qos.logback.classic:Name=default,Type=ch.qos.logback.classic.jmx.JMXConfigurator", "LoggerList");
		return getClient(managementUrl).execute(request).getValue();
	}
	
	public Map<String, LoggerLevel> getLoggerEfectiveLevels(String managementUrl, List<String> loggers) throws MalformedObjectNameException, J4pException {
		List<J4pExecRequest> requests = new ArrayList<>();
		for (String logger : loggers) {
			J4pExecRequest request = new J4pExecRequest("ch.qos.logback.classic:Name=default,Type=ch.qos.logback.classic.jmx.JMXConfigurator", "getLoggerEffectiveLevel", logger);
			requests.add(request);
		}
		Map<String, LoggerLevel> levels = new LinkedHashMap<String, LoggerLevel>();
		List<J4pResponse<J4pExecRequest>> execute = getClient(managementUrl).execute(requests);
		for (J4pResponse<J4pExecRequest> res : execute) {
			levels.put((String) res.getRequest().getArguments().get(0), LoggerLevel.valueOf((String) res.getValue()));
		}
		return levels;
	}
	
	public String setLoggerLevel(String managementUrl, String logger, LoggerLevel level) throws MalformedObjectNameException, J4pException {
		J4pExecRequest request = new J4pExecRequest("ch.qos.logback.classic:Name=default,Type=ch.qos.logback.classic.jmx.JMXConfigurator", "setLoggerLevel", logger, level);
		return getClient(managementUrl).execute(request).getValue();
	}
	
}
