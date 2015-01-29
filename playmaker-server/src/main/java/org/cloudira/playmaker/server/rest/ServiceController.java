package org.cloudira.playmaker.server.rest;

import java.util.ArrayList;
import java.util.List;

import org.cloudira.playmaker.server.discovery.ServiceDiscovery;
import org.cloudira.playmaker.server.domain.Service;
import org.cloudira.playmaker.server.domain.ServiceInstance;
import org.cloudira.playmaker.server.repository.ServiceRepository;
import org.cloudira.playmaker.server.rest.converter.InstanceConverter;
import org.cloudira.playmaker.server.rest.converter.ServiceConverter;
import org.cloudira.playmaker.server.rest.converter.ServiceDetailsConverter;
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
	private ServiceConverter serviceConverter;
	
	@Autowired
	private ServiceDetailsConverter detailsConverter;
	
	@Autowired
	private InstanceConverter instanceConverter;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<ServiceResource> findServices() {
		List<ServiceResource> list = new ArrayList<ServiceResource>();
		
		for (Service service : serviceRepository.findAll()) {
			list.add(serviceConverter.convert(service));
		}
		
		return list;
	}
	
	@RequestMapping(value = "/{serviceId}", method = RequestMethod.GET)
	public ServiceDetailsResource findService(@PathVariable int serviceId) {
		Service service = serviceRepository.findOne(serviceId);
		if (service != null) {
			ServiceDetailsResource res = detailsConverter.convert(service);
			
			List<InstanceResource> instances = new ArrayList<InstanceResource>();
			for (ServiceInstance si : serviceDiscovery.findServiceInstances(serviceId)) {
				instances.add(instanceConverter.convert(si));
			}
			
			res.setInstances(instances);
			
			return res;
		}
		
		return null;
	}
	
}
