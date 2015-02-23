package org.cloudira.playmaker.client.rest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.discovery.AbstractDiscoveryLifecycle;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonInclude;

@Configuration
@EnableConfigurationProperties({ServerProperties.class, RestDiscoveryClientConfiguration.class})
@ConditionalOnMissingBean(DiscoveryClient.class)
public class RestDiscoveryClientAutoConfiguration extends AbstractDiscoveryLifecycle {
	
	protected static final Logger log = LoggerFactory.getLogger(RestDiscoveryClientAutoConfiguration.class);

	private DefaultServiceInstance serviceInstance;

	@PostConstruct
	public void init() {
		String host = "localhost";
		try {
			host = InetAddress.getLocalHost().getHostName();
		}
		catch (UnknownHostException e) {
			log.error("Cannot get host info", e);
		}
		int port = findPort();
		this.serviceInstance = new DefaultServiceInstance(getEnvironment().getProperty(
				"spring.application.name", "application"), host, port, false);
	}

	private int findPort() {
		int port = getServerProperties().getPort();
		if (shouldRegisterManagement()) {
			return getManagementPort();
		}
		return port;
	}
	
	public RestTemplate discoveryRestTemplate() {
		RestTemplate template = new RestTemplate();
		StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
		stringConverter.setWriteAcceptCharset(false);

		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mappingJackson2HttpMessageConverter.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		mappingJackson2HttpMessageConverter.getObjectMapper().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		mappingJackson2HttpMessageConverter.setPrettyPrint(true);

		template.getMessageConverters().clear();
		template.getMessageConverters().add(new ByteArrayHttpMessageConverter());
		template.getMessageConverters().add(stringConverter);
		template.getMessageConverters().add(mappingJackson2HttpMessageConverter);
		template.getMessageConverters().add(new FormHttpMessageConverter());
		return template;
	}

	@Bean
	public DiscoveryClient discoveryClient() {
		return new RestDiscoveryClient(discoveryRestTemplate(), this.serviceInstance);
	}

	@Override
	protected Object getConfiguration() {
		return getEnvironment();
	}

	@Override
	protected void register() {
		log.debug("Registeeeeeeeeeeer!!!");
		
	}

	@Override
	protected void deregister() {
		log.debug("Deregisteeeeeeeeeeer!!!");
	}

	@Override
	protected boolean isEnabled() {
		return true;
	}
	
	private ServerProperties getServerProperties() {
		try {
			return getContext().getBean(ServerProperties.class);
		} catch (NoSuchBeanDefinitionException e) {
			return null;
		}
	}

}
