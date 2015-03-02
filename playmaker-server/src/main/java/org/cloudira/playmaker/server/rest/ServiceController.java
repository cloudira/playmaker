package org.cloudira.playmaker.server.rest;

import java.util.ArrayList;
import java.util.List;

import org.cloudira.playmaker.server.discovery.ServiceDiscovery;
import org.cloudira.playmaker.server.domain.ConfigurationItem;
import org.cloudira.playmaker.server.domain.ConfigurationMap;
import org.cloudira.playmaker.server.domain.ConfigurationProperty;
import org.cloudira.playmaker.server.domain.Profile;
import org.cloudira.playmaker.server.domain.Service;
import org.cloudira.playmaker.server.repository.ConfigurationRepository;
import org.cloudira.playmaker.server.repository.ProfileRepository;
import org.cloudira.playmaker.server.repository.ServiceRepository;
import org.cloudira.playmaker.server.rest.converter.RestConversionService;
import org.cloudira.playmaker.server.rest.resource.ConfigurationMapResource;
import org.cloudira.playmaker.server.rest.resource.ConfigurationPropertyResource;
import org.cloudira.playmaker.server.rest.resource.ConfigurationItemResource;
import org.cloudira.playmaker.server.rest.resource.ConfigurationResource;
import org.cloudira.playmaker.server.rest.resource.IdNameResource;
import org.cloudira.playmaker.server.rest.resource.InstanceResource;
import org.cloudira.playmaker.server.rest.resource.ServiceDetailsResource;
import org.cloudira.playmaker.server.rest.resource.ServiceResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

	@Autowired
	private ServiceRepository serviceRepository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private ConfigurationRepository configurationRepository;
	
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
	
	@RequestMapping(value = "/{serviceId}/config", method = RequestMethod.GET)
	public ConfigurationResource getConfiguration(@PathVariable int serviceId) {
		return getConfiguration(serviceId, "default", 0);
	}
	
	@RequestMapping(value = "/{serviceId}/config/{profileName}", method = RequestMethod.GET)
	public ConfigurationResource getConfiguration(@PathVariable int serviceId, @PathVariable String profileName) {
		return getConfiguration(serviceId, profileName, 0);
	}
	
	@RequestMapping(value = "/{serviceId}/config/{profileName}/{itemId}", method = RequestMethod.GET)
	public ConfigurationResource getConfiguration(@PathVariable int serviceId, @PathVariable String profileName, @PathVariable int itemId) {
		Service service = serviceRepository.findOne(serviceId);
		if (service == null) {
			return null;
		}
		
		Profile profile = profileRepository.findByName(profileName);
		if (profile == null) {
			return null;
		}
		
		ConfigurationItem item = null;
		
		if (itemId == 0) {
			ConfigurationMap root = new ConfigurationMap();
			root.setName("root");
			root.setService(service);
			root.setProfile(profile);
			root.setItems(configurationRepository.findByProfileAndServiceAndParentIsNull(profile, service));
			
			item = root;
		} else {
			item = configurationRepository.findOne(itemId);
		}
		
		
		if (item == null || item.getService() == null || item.getService().getId() != service.getId()) {
			return null;
		}
		
		if (item.getProfile().getId() != profile.getId()) {
			return null;
		}
		
		if (item instanceof ConfigurationMap) {
			ConfigurationMap map = (ConfigurationMap) item;
			ConfigurationMapResource res = conversionService.convert(map, ConfigurationMapResource.class);
			
			for (ConfigurationItem i : map.getItems()) {
				if (i instanceof ConfigurationMap) {
					res.getMaps().add(conversionService.convert((ConfigurationMap) i, ConfigurationMapResource.class));
				}
				
				if (i instanceof ConfigurationProperty) {
					res.getProperties().add(conversionService.convert((ConfigurationProperty) i, ConfigurationPropertyResource.class));
				}
			}
			
			List<IdNameResource> path = new ArrayList<IdNameResource>();
			
			
			if (item.getId() != 0) {
				populatePath(item, path);
			}
			
			ConfigurationResource config = new ConfigurationResource();
			config.setProfile(profile.getName());
			config.setItem(res);
			config.setPath(path);
				
			return config;
		}
		
		if (item instanceof ConfigurationProperty) {
			ConfigurationProperty prop = (ConfigurationProperty) item;
			ConfigurationPropertyResource res = conversionService.convert(prop, ConfigurationPropertyResource.class);
			
			List<IdNameResource> path = new ArrayList<IdNameResource>();
			if (item.getId() != 0) {
				populatePath(item, path);
			}
			
			ConfigurationResource config = new ConfigurationResource();
			config.setProfile(profile.getName());
			config.setItem(res);
			config.setPath(path);
			
			return config;
		}
		
		return null;
	}
	
	@RequestMapping(value = "/{serviceId}/config/{profileName}/{itemId}", method = RequestMethod.POST)
	public void addConfiguration(@PathVariable int serviceId, @PathVariable String profileName, @PathVariable int itemId, @RequestBody ConfigurationItemResource res) {
		Service service = serviceRepository.findOne(serviceId);
		if (service == null) {
			return;
		}
		
		Profile profile = profileRepository.findByName(profileName);
		if (profile == null) {
			return;
		}
		
		ConfigurationMap parent = null;
		if (itemId != 0) {
			parent = (ConfigurationMap) configurationRepository.findOne(itemId);
			if (parent == null) {
				return;
			}
		}
		
		if (res instanceof ConfigurationMapResource) {
			ConfigurationMap map = new ConfigurationMap();
			map.setProfile(profile);
			map.setService(service);
			map.setParent(parent);
			map.setName(res.getName());
			
			configurationRepository.save(map);
		} else if (res instanceof ConfigurationPropertyResource) {
			ConfigurationProperty prop = new ConfigurationProperty();
			prop.setProfile(profile);
			prop.setService(service);
			prop.setParent(parent);
			prop.setName(res.getName());
			prop.setValue(((ConfigurationPropertyResource) res).getValue());
			
			configurationRepository.save(prop);
		}
		
	}
	
	@RequestMapping(value = "/{serviceId}/config/{profileName}/{itemId}", method = RequestMethod.PUT)
	public void setConfiguration(@PathVariable int serviceId, @PathVariable String profileName, @PathVariable int itemId, @RequestBody ConfigurationItemResource res) {
		Service service = serviceRepository.findOne(serviceId);
		if (service == null) {
			return;
		}
		
		Profile profile = profileRepository.findByName(profileName);
		if (profile == null) {
			return;
		}
		
		ConfigurationProperty prop = (ConfigurationProperty) configurationRepository.findOne(itemId);
		if (prop == null) {
			return;
		}
		
		if (res instanceof ConfigurationPropertyResource) {
			prop.setValue(((ConfigurationPropertyResource) res).getValue());
			
			configurationRepository.save(prop);
		}
	}
	
	@RequestMapping(value = "/{serviceId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updateService(@PathVariable int serviceId, @RequestBody ServiceResource res) {
		Service service = serviceRepository.findOne(serviceId);
		if (service != null) {
			service.setDescription(res.getDescription());
			
			serviceRepository.save(service);
		}
	}
	
	public void populatePath(ConfigurationItem item, List<IdNameResource> path) {
		IdNameResource res = new IdNameResource();
		res.setId(item.getId());
		res.setName(item.getName());
		
		path.add(0, res);
		
		if (item.getParent() != null) {
			populatePath(item.getParent(), path);
		}
	}
	
}
