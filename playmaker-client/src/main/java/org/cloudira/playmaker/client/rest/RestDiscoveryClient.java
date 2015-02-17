package org.cloudira.playmaker.client.rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.cloudira.playmaker.client.rest.resource.InstanceResource;
import org.cloudira.playmaker.client.rest.resource.ServiceDetailsResource;
import org.cloudira.playmaker.client.rest.resource.ServiceResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class RestDiscoveryClient implements DiscoveryClient, Runnable {
	
	public static final String DESCRIPTION = "Cloudira PlayMaker REST Discovery Client";
	
	@Autowired
	public RestDiscoveryClientConfiguration configuration;

	private final RestTemplate restTemplate;
	private final ServiceInstance instance;
	private ScheduledExecutorService executor;

	public RestDiscoveryClient(RestTemplate restTemplate, ServiceInstance instance) {
		this.restTemplate = restTemplate;
		this.instance = instance;
	}
	
	@PostConstruct
	public void init() {
		log.info("URI: {}, registrationInterval: {}", configuration.getUri(), configuration.getRegistrationInterval());
		executor = Executors.newScheduledThreadPool(1); // TODO: koristiti @Scheduled ili ovo? (za @Scheduled mora biti @EnableScheduling)
		executor.scheduleWithFixedDelay(this, configuration.getRegistrationInterval(), configuration.getRegistrationInterval(), TimeUnit.MILLISECONDS);
	}
	
	@PreDestroy
	public void stop() {
		if (executor != null) {
			executor.shutdownNow();
		}
	}
	
	@Override
	public void run() {
		Thread.currentThread().setName(getLocalServiceInstance().getServiceId() + "-RegistrationThread-" + Thread.currentThread().getId());
		log.info("Register....");
		// TODO: registration, caching of instances, ...
	}

	@Override
	public String description() {
		log.debug("description() -> {}", DESCRIPTION);
		return DESCRIPTION;
	}

	@Override
	public ServiceInstance getLocalServiceInstance() {
		log.debug("getLocalServiceInstance() -> {}", instance);
		return this.instance;
	}

	@Override
	public List<ServiceInstance> getInstances(String serviceId) {
		log.debug("getInstances(serviceId={})", serviceId);
		
		ServiceResource service = null;
		for (ServiceResource resource : getAllServices()) {
			if (ObjectUtils.nullSafeEquals(resource.getName(), serviceId)) {
				service = resource;
			}
		}
		
		if (service != null) {
			ServiceDetailsResource serviceDetails = getServiceDetails(service.getId());
			if (serviceDetails != null) {
				List<ServiceInstance> instances = new ArrayList<>();
				
				for (InstanceResource resource : serviceDetails.getInstances()) {
					instances.add(new RestServiceInstance(service, resource));
				}
				
				return instances;
			}
		}
		
		return Collections.emptyList();
	}
	
	public List<ServiceInstance> getAllInstances() {
		log.debug("getAllInstances()");
		
		List<ServiceInstance> instances = new ArrayList<>();
		for (ServiceResource service : getAllServices()) {
			ServiceDetailsResource serviceDetails = getServiceDetails(service.getId());
			if (serviceDetails != null) {
				for (InstanceResource resource : serviceDetails.getInstances()) {
					instances.add(new RestServiceInstance(service, resource));
				}
			}
		}
		return instances;
	}

	@Override
	public List<String> getServices() {
		List<String> services = new ArrayList<>();
		for (ServiceResource r : getAllServices()) {
			services.add(r.getName());
		}
		return services;
	}
	
	private List<ServiceResource> getAllServices() {
		return Arrays.asList(restTemplate.getForObject(configuration.getUri() + "/services", ServiceResource[].class, new Object[]{}));
	}
	
	private ServiceDetailsResource getServiceDetails(long serviceId) {
		return restTemplate.getForObject(configuration.getUri() + "/services/{serviceId}", ServiceDetailsResource.class, serviceId);
	}
	
	@Data
	static class RestServiceInstance implements ServiceInstance {
		
		private ServiceResource service;
		private InstanceResource instance;
		
		RestServiceInstance(ServiceResource service, InstanceResource instance) {
			this.service = service;
			this.instance = instance;
		}

		@Override
		public String getServiceId() {
			return service.getName();
		}

		@Override
		public String getHost() {
			return instance.getHost();
		}

		@Override
		public int getPort() {
			return instance.getPort();
		}

		@Override
		public boolean isSecure() {
			return false;
		}

		@Override
		public URI getUri() {
			String uri = String.format("http%s://%s:%s", (isSecure() ? "s" : ""), getHost(), getPort());
			return URI.create(uri);
		}
		
	}

}
