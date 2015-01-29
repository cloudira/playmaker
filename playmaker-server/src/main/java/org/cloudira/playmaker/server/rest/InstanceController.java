package org.cloudira.playmaker.server.rest;

import org.cloudira.playmaker.server.discovery.ServiceDiscovery;
import org.cloudira.playmaker.server.domain.ServiceInstance;
import org.cloudira.playmaker.server.repository.ServiceRepository;
import org.cloudira.playmaker.server.rest.converter.InstanceConverter;
import org.cloudira.playmaker.server.rest.converter.ServiceConverter;
import org.cloudira.playmaker.server.rest.converter.ServiceDetailsConverter;
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
	private ServiceConverter serviceConverter;
	
	@Autowired
	private ServiceDetailsConverter detailsConverter;
	
	@Autowired
	private InstanceConverter instanceConverter;
	
	@RequestMapping(value = "/{instanceId}", method = RequestMethod.GET)
	public InstanceResource findService(@PathVariable int instanceId) {
		ServiceInstance serviceInstance = serviceDiscovery.findServiceInstance(instanceId);
		if (serviceInstance != null) {
			return instanceConverter.convert(serviceInstance);
		}
		
		return null;
	}
	
}
