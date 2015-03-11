package org.cloudira.playmaker.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cloudira.playmaker.server.domain.ConfigurationItem;
import org.cloudira.playmaker.server.domain.ConfigurationMap;
import org.cloudira.playmaker.server.domain.ConfigurationProperty;
import org.cloudira.playmaker.server.domain.Profile;
import org.cloudira.playmaker.server.domain.Service;
import org.cloudira.playmaker.server.repository.ConfigurationRepository;
import org.cloudira.playmaker.server.repository.ProfileRepository;
import org.cloudira.playmaker.server.repository.ServiceRepository;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.EnvironmentRepository;

public class PlayMakerEnvironmentRepository implements EnvironmentRepository {
	
	private ServiceRepository serviceRepository;
	
	private ProfileRepository profileRepository;
	
	private ConfigurationRepository configurationRepository;
	
	public PlayMakerEnvironmentRepository(ServiceRepository serviceRepository, ProfileRepository profileRepository, ConfigurationRepository configurationRepository) {
		this.serviceRepository = serviceRepository;
		this.profileRepository = profileRepository;
		this.configurationRepository = configurationRepository;
	}
	
	@Override
	public Environment findOne(String application, String profile, String label) {
		Environment env = new Environment(profile, "");
		
		Service service = serviceRepository.findByName(application);
		if (service != null) {
			Profile p = profileRepository.findByName(profile);
			if (p != null) {
				Map<String, String> properties = getProperties(service, p);
				env.add(new PropertySource(application + "-" + profile + ".properties", properties));
			}
			
			Profile d = profileRepository.findByName("default");
			if (d != null) {
				Map<String, String> properties = getProperties(service, d);
				env.add(new PropertySource(application + ".properties", properties));
			}
		}
		
		return env;
	}
	
	protected Map<String, String> getProperties(Service service, Profile profile) {
		List<ConfigurationItem> items = configurationRepository.findByProfileAndServiceAndParentIsNull(profile, service);
		
		Map<String, String> properties = new HashMap<String, String>();
		populateProperties("", items, properties);
		
		return properties;
	}
	
	private void populateProperties(String name, List<ConfigurationItem> items, Map<String, String> properties) {
		for (ConfigurationItem item : items) {
			name = name + item.getName();
			if (item instanceof ConfigurationProperty) {
				ConfigurationProperty prop = (ConfigurationProperty) item;
				properties.put(name, prop.getValue());
			} else {
				ConfigurationMap map = (ConfigurationMap) item;
				populateProperties(name + ".", map.getItems(), properties);
			}
		}
	}

}
