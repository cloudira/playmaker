package org.cloudira.playmaker.server.rest;

import java.util.List;

import org.cloudira.playmaker.server.discovery.ServiceDiscovery;
import org.cloudira.playmaker.server.domain.Service;
import org.cloudira.playmaker.server.repository.ServiceRepository;
import org.cloudira.playmaker.server.rest.converter.RestConversionService;
import org.cloudira.playmaker.server.rest.resource.InstanceResource;
import org.cloudira.playmaker.server.rest.resource.ServiceDetailsResource;
import org.cloudira.playmaker.server.rest.resource.ServiceResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

	@Autowired
	private ServiceRepository serviceRepository;
	
	@Autowired
	private ServiceDiscovery serviceDiscovery;
	
	@Autowired
	private RestConversionService conversionService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<ServiceResource> findServices() {
		return conversionService.convertCollection(serviceRepository.findAll(), List.class, ServiceResource.class);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/{serviceId}", method = RequestMethod.GET)
	public ServiceDetailsResource findService(@PathVariable int serviceId) {
		Service service = serviceRepository.findOne(serviceId);
		if (service != null) {
			ServiceDetailsResource res = conversionService.convert(service, ServiceDetailsResource.class);
			
			res.setInstances(conversionService.convertCollection(serviceDiscovery.findServiceInstances(serviceId), List.class, InstanceResource.class));
			
			return res;
		}
		
		return null;
	}
	
}
