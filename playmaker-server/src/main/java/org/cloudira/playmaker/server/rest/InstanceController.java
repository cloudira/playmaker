package org.cloudira.playmaker.server.rest;

import org.cloudira.playmaker.server.discovery.ServiceDiscovery;
import org.cloudira.playmaker.server.domain.ServiceInstance;
import org.cloudira.playmaker.server.repository.ServiceRepository;
import org.cloudira.playmaker.server.rest.converter.RestConversionService;
import org.cloudira.playmaker.server.rest.resource.InstanceResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/instances")
public class InstanceController {

	@Autowired
	private ServiceRepository serviceRepository;
	
	@Autowired
	private ServiceDiscovery serviceDiscovery;
	
	@Autowired
	private RestConversionService conversionService;
	
	@RequestMapping(value = "/{instanceId}", method = RequestMethod.GET)
	public InstanceResource findService(@PathVariable int instanceId) {
		ServiceInstance serviceInstance = serviceDiscovery.findServiceInstance(instanceId);
		return conversionService.convert(serviceInstance, InstanceResource.class);
	}
	
}
