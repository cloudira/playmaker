package org.cloudira.playmaker.server.discovery;

import java.util.List;

import org.cloudira.playmaker.server.domain.Service;
import org.cloudira.playmaker.server.domain.ServiceInstance;
import org.cloudira.playmaker.server.repository.ServiceInstanceRepository;
import org.cloudira.playmaker.server.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class ServiceDiscovery {
	
	@Autowired
	private ServiceRepository serviceRepository;
	
	@Autowired
	private ServiceInstanceRepository instanceRepository;

	public List<ServiceInstance> findServiceInstances(int serviceId) {
		Service service = serviceRepository.findOne(serviceId);
		return service.getInstances();
	}
	
	public ServiceInstance findServiceInstance(int instanceId) {
		return instanceRepository.findOne(instanceId);
	}
	
}
