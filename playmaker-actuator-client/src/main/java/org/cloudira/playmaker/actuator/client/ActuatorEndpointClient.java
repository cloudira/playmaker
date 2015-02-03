package org.cloudira.playmaker.actuator.client;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;

public abstract class ActuatorEndpointClient<T> extends RestTemplate {
	
	public ActuatorEndpointClient() {
		StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
		stringConverter.setWriteAcceptCharset(false);

		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mappingJackson2HttpMessageConverter.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		mappingJackson2HttpMessageConverter.getObjectMapper().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		mappingJackson2HttpMessageConverter.setPrettyPrint(true);

		getMessageConverters().clear();
		getMessageConverters().add(new ByteArrayHttpMessageConverter());
		getMessageConverters().add(stringConverter);
		getMessageConverters().add(mappingJackson2HttpMessageConverter);
		getMessageConverters().add(new FormHttpMessageConverter());
	}

	public String getEndpointPath() {
		return "";
	}
	
	public HttpMethod getHttpMethod() {
		return HttpMethod.GET;
	}

	protected ParameterizedTypeReference<T> getParameterizedTypeReference() {
		Type genericSuperclass = getClass().getGenericSuperclass();
		if (genericSuperclass instanceof ParameterizedType) {
			final Type type = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
			return new ParameterizedTypeReference<T>() {
				@Override
				public Type getType() {
					return type;
				}
			};
		}
		return new ParameterizedTypeReference<T>() {};
	}

	protected String getUrl(String managementUrl, String... pathSegments) {
		return UriComponentsBuilder.fromHttpUrl(managementUrl).pathSegment(getEndpointPath()).pathSegment(pathSegments).build().toString();
	}

	public <V> V invoke(String managementUrl, Class<V> valueType) {
		return getForObject(getUrl(managementUrl), valueType, Collections.emptyMap());
	}

	public <V> V invoke(String managementUrl, String path, Class<V> valueType) {
		return getForObject(getUrl(managementUrl, path), valueType, Collections.emptyMap());
	}

	public <V> V invoke(String managementUrl, String path, Class<V> valueType, Map<String, Object> pathVariables) {
		return getForObject(getUrl(managementUrl, path), valueType, pathVariables);
	}

	public <V> V invoke(String managementUrl, String path, Class<V> valueType, Object... pathVariables) {
		return getForObject(getUrl(managementUrl, path), valueType, pathVariables);
	}

	public T invoke(String managementUrl) {
		return (T) exchange(getUrl(managementUrl), getHttpMethod(), null, getParameterizedTypeReference(), Collections.emptyMap()).getBody();
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "<" + getParameterizedTypeReference().getType() + "@" + getHttpMethod() + " : " + getEndpointPath() + ">";
	}
}
