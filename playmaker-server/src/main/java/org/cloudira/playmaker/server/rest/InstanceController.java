package org.cloudira.playmaker.server.rest;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.cloudira.playmaker.actuator.client.BeansEndpointClient;
import org.cloudira.playmaker.actuator.client.EnvironmentEndpointClient;
import org.cloudira.playmaker.actuator.client.HealthEndpointClient;
import org.cloudira.playmaker.actuator.client.InfoEndpointClient;
import org.cloudira.playmaker.actuator.client.MetricsEndpointClient;
import org.cloudira.playmaker.actuator.client.resource.ContextBeans;
import org.cloudira.playmaker.actuator.client.resource.ContextBeans.Bean;
import org.cloudira.playmaker.actuator.client.resource.Environment;
import org.cloudira.playmaker.actuator.client.resource.Environment.PropertySource;
import org.cloudira.playmaker.actuator.client.resource.Metrics;
import org.cloudira.playmaker.server.discovery.ServiceDiscovery;
import org.cloudira.playmaker.server.domain.ServiceInstance;
import org.cloudira.playmaker.server.repository.ServiceRepository;
import org.cloudira.playmaker.server.rest.converter.RestConversionService;
import org.cloudira.playmaker.server.rest.resource.BeanResource;
import org.cloudira.playmaker.server.rest.resource.ContextResource;
import org.cloudira.playmaker.server.rest.resource.InstanceEnvironmentResource;
import org.cloudira.playmaker.server.rest.resource.InstanceDetailsResource;
import org.cloudira.playmaker.server.rest.resource.InstanceResource;
import org.cloudira.playmaker.server.rest.resource.MetricsResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
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
	
	@Autowired
	private InfoEndpointClient infoEndpoint;
	
	@Autowired
	private HealthEndpointClient healthEndpoint;
	
	@Autowired
	private MetricsEndpointClient metricsEndpoint;
	
	@Autowired
	private EnvironmentEndpointClient environmentEndpoint;
	
	@Autowired
	private BeansEndpointClient beansEndpoint;
	
	@RequestMapping(value = "/{instanceId}", method = RequestMethod.GET)
	public InstanceResource findService(@PathVariable int instanceId) {
		ServiceInstance serviceInstance = serviceDiscovery.findServiceInstance(instanceId);
		return conversionService.convert(serviceInstance, InstanceResource.class);
	}
	
	@RequestMapping(value = "/{instanceId}/details", method = RequestMethod.GET)
	public InstanceDetailsResource getDetails(@PathVariable int instanceId) {
		String adminUrl = serviceDiscovery.getAdminUrl(instanceId);
		
		Map<String, Object> info = infoEndpoint.invoke(adminUrl);
		Map<String, Object> metrics = metricsEndpoint.invoke(adminUrl);
		
		Object artifactId = info.get("artifactId");
		Object groupId = info.get("groupId");
		Object version = info.get("version");
		
		int processors = (Integer) metrics.get("processors");
		
		int threads = (Integer) metrics.get("threads");
		int threadDaemon = (Integer) metrics.get("threads.daemon");
		int threadPeak = (Integer) metrics.get("threads.peak");
		
		int classes = (Integer) metrics.get("classes");
		int classesLoaded = (Integer) metrics.get("classes.loaded");
		int classesUnloaded = (Integer) metrics.get("classes.unloaded");
		
		int psScavengeCount = (Integer) metrics.get("gc.ps_scavenge.count");
		int psScavengeTime = (Integer) metrics.get("gc.ps_scavenge.time");
		int psMarksweepCount = (Integer) metrics.get("gc.ps_marksweep.count");
		int psMarksweepTime = (Integer) metrics.get("gc.ps_marksweep.time");
		
		int mem = (Integer) metrics.get("mem");
		int memFree = (Integer) metrics.get("mem.free");
		
		InstanceDetailsResource res = new InstanceDetailsResource();
		res.setArtifactId(artifactId != null ? artifactId.toString() : null);
		res.setGroupId(groupId != null ? groupId.toString() : null);
		res.setVersion(version != null ? version.toString() : null);
		res.setStatus("UP");
		res.setUptime("00:00:57");
		res.setProcessors(processors);
		res.setClasses(classes);
		res.setClassesLoaded(classesLoaded);
		res.setClassesUnloaded(classesUnloaded);
		res.setThreads(threads);
		res.setThreadsDeamon(threadDaemon);
		res.setThreadsPeak(threadPeak);
		res.setMemoryTotal(formatMemory(mem));
		res.setMemoryUsed(formatMemory(mem - memFree));
		res.setMemoryPercent(27);
		res.setHeapInitial("128.00M");
		res.setHeapMax("1,810.00M");
		res.setHeapTotal("194.50M");
		res.setHeapPercent(27);
		res.setPsScavengeCount(psScavengeCount);
		res.setPsScavengeTime(psScavengeTime);
		res.setPsMarksweepCount(psMarksweepCount);
		res.setPsMarksweepTime(psMarksweepTime);
		
		return res;
	}
	
	@RequestMapping(value = "/{instanceId}/env", method = RequestMethod.GET)
	public List<InstanceEnvironmentResource> getEnvironment(@PathVariable int instanceId) {
		String adminUrl = serviceDiscovery.getAdminUrl(instanceId);
			
		List<InstanceEnvironmentResource> list = new ArrayList<InstanceEnvironmentResource>();
		
		Environment env = environmentEndpoint.invoke(adminUrl);
		for (PropertySource propertySource : env.getPropertySources()) {
			if (!CollectionUtils.isEmpty(propertySource.getProperties())) {
				InstanceEnvironmentResource res = new InstanceEnvironmentResource();
				res.setName(propertySource.getName());
				res.setProperties(propertySource.getProperties());
				
				list.add(res);
			}
		}
		
		if (!CollectionUtils.isEmpty(list)) {
			list.get(0).setOpen(true);
		}
		
		return list;
	}
	
	@RequestMapping(value = "/{instanceId}/metrics", method = RequestMethod.GET)
	public MetricsResource getMetrics(@PathVariable int instanceId) {
		String adminUrl = serviceDiscovery.getAdminUrl(instanceId);
		
		Map<String, Integer> counters = new TreeMap<String, Integer>();
		Map<String, Double> gauges = new TreeMap<String, Double>();
		
		for (Map.Entry<String, Object> entry : metricsEndpoint.invoke(adminUrl).entrySet()) {
			if (entry.getKey().startsWith("counter.")) {
				String key = entry.getKey().substring("counter.".length());
				counters.put(key, (Integer) entry.getValue());
			}
			
			if (entry.getKey().startsWith("gauge.")) {
				String key = entry.getKey().substring("gauge.".length());
				gauges.put(key, (Double) entry.getValue());
			}
		}
		
		MetricsResource res = new MetricsResource();
		res.setCounters(counters);
		res.setGauges(gauges);
		
		return res;
	}
	
	@RequestMapping(value = "/{instanceId}/beans", method = RequestMethod.GET)
	public List<ContextResource> getBeans(@PathVariable int instanceId) {
		String adminUrl = serviceDiscovery.getAdminUrl(instanceId);
		
		List<ContextResource> contexts = new ArrayList<ContextResource>();
		
		for (ContextBeans context : beansEndpoint.invoke(adminUrl)) {
			
			ContextResource contextResource = new ContextResource();
			if (!StringUtils.isEmpty(context.getParent())) {
				contextResource.setName(context.getParent() + " / " + context.getContext());
			} else {
				contextResource.setName(context.getContext());
			}
			
			List<BeanResource> beans = new ArrayList<BeanResource>();
			
			for (Bean bean : context.getBeans()) {
				BeanResource beanResource = new BeanResource();
				beanResource.setName(bean.getBean());
				beanResource.setScope(bean.getScope());
				beanResource.setType(bean.getType());
				//TODO beanResource.setResource(bean.getResource());
				beanResource.setDependencies(bean.getDependencies());
				
				beans.add(beanResource);
			}
			
			contextResource.setBeans(beans);
			
			contexts.add(contextResource);
		}
		
		if (!CollectionUtils.isEmpty(contexts)) {
			contexts.get(0).setOpen(true);
		}
		
		return contexts;
	}
	
	public static String formatMemory(long size) {
	    if(size <= 0) return "0";
	    final String[] units = new String[] { "B", "kB", "MB", "GB", "TB" };
	    int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
	    return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	    //return new DecimalFormat("#,##0.#").format(size) + "M";
	}
	
}
