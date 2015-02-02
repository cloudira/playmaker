package org.cloudira.playmaker.server.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cloudira.playmaker.server.discovery.ServiceDiscovery;
import org.cloudira.playmaker.server.domain.ServiceInstance;
import org.cloudira.playmaker.server.repository.ServiceRepository;
import org.cloudira.playmaker.server.rest.converter.RestConversionService;
import org.cloudira.playmaker.server.rest.resource.BeanResource;
import org.cloudira.playmaker.server.rest.resource.ContextResource;
import org.cloudira.playmaker.server.rest.resource.EnvironmentResource;
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
	
	@RequestMapping(value = "/{instanceId}/env", method = RequestMethod.GET)
	public List<EnvironmentResource> getEnvironment(@PathVariable int instanceId) {
		List<EnvironmentResource> list = new ArrayList<EnvironmentResource>();
		
		EnvironmentResource res1 = new EnvironmentResource();
		res1.setName("applicationConfig: [classpath:/bootstrap.yml]");
		
		Map<String, String> props1 = new HashMap<String, String>();
		props1.put("key1", "value1");
		props1.put("key2", "value2");
		
		res1.setProperties(props1);
		
		EnvironmentResource res2 = new EnvironmentResource();
		res2.setName("defaultProperties");
		
		Map<String, String> props2 = new HashMap<String, String>();
		props2.put("key3", "value3");
		props2.put("key4", "value4");
		
		res2.setProperties(props2);
		
		list.add(res1);
		list.add(res2);
		
		return list;
	}
	
	@RequestMapping(value = "/{instanceId}/beans", method = RequestMethod.GET)
	public ContextResource getBeans(@PathVariable int instanceId) {
		ContextResource root = new ContextResource();
		root.setName("bootstrap");
		
		BeanResource bean1 = new BeanResource();
		bean1.setName("propertySourceBootstrapConfiguration");
		bean1.setScope("singleton");
		bean1.setType("org.springframework.cloud.bootstrap.config.PropertySourceBootstrapConfiguration$$EnhancerBySpringCGLIB$$ae7483a9");
		bean1.setResource(null);
		
		List<String> d1 = new ArrayList<String>();
		d1.add("spring.cloud.config.CONFIGURATION_PROPERTIES");
		
		bean1.setDependencies(d1);
		
		List<BeanResource> beans1 = new ArrayList<BeanResource>();
		beans1.add(bean1);
		
		root.setBeans(beans1);
		
		ContextResource playmaker = new ContextResource();
		playmaker.setName("playmaker:8888");
		
		BeanResource bean2 = new BeanResource();
		bean2.setName("playmakerApplication");
		bean2.setScope("singleton");
		bean2.setType("org.cloudira.playmaker.server.PlayMakerApplication$$EnhancerBySpringCGLIB$$77449532");
		bean2.setResource(null);
		
		List<BeanResource> beans2 = new ArrayList<BeanResource>();
		beans2.add(bean2);
		
		playmaker.setBeans(beans2);
		
		List<ContextResource> contexts = new ArrayList<ContextResource>();
		contexts.add(playmaker);
		
		root.setContexts(contexts);
		
		return root;
	}
	
}
