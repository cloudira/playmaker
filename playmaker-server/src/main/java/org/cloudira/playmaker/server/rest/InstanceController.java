package org.cloudira.playmaker.server.rest;

import org.cloudira.playmaker.server.discovery.ServiceDiscovery;
import org.cloudira.playmaker.server.domain.ServiceInstance;
import org.cloudira.playmaker.server.repository.ServiceRepository;
import org.cloudira.playmaker.server.rest.converter.RestConversionService;
import org.cloudira.playmaker.server.rest.resource.InstanceDetailsResource;
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
	
	@RequestMapping(value = "/{instanceId}/details", method = RequestMethod.GET)
	public InstanceDetailsResource findDetails(@PathVariable int instanceId) {
		InstanceDetailsResource res = new InstanceDetailsResource();
		res.setArtifactId("playmaker");
		res.setGroupId("org.cloudira.playmaker");
		res.setVersion("0.0.1-SNAPSHOT");
		res.setStatus("UP");
		res.setUptime("00:00:57");
		res.setProcessors(8);
		res.setClasses(9976);
		res.setClassesLoaded(9977);
		res.setClassesUnloaded(1);
		res.setThreads(33);
		res.setThreadsDeamon(31);
		res.setThreadsPeak(33);
		res.setMemoryTotal("194.50M");
		res.setMemoryUsed("51.73M");
		res.setMemoryPercent(27);
		res.setHeapInitial("128.00M");
		res.setHeapMax("1,810.00M");
		res.setHeapTotal("194.50M");
		res.setHeapPercent(27);
		res.setPsScavengeCount(14);
		res.setPsScavengeTime(161);
		res.setPsMarksweepCount(2);
		res.setPsMarksweepTime(214);
		
		return res;
	}
	
}
