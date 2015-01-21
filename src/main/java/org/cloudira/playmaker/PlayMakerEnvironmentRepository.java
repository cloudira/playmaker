package org.cloudira.playmaker;

import org.cloudira.playmaker.domain.ServiceDetils;
import org.cloudira.playmaker.domain.ServiceProfile;
import org.cloudira.playmaker.repository.ServiceRepository;
import org.springframework.cloud.config.Environment;
import org.springframework.cloud.config.PropertySource;
import org.springframework.cloud.config.server.EnvironmentRepository;

public class PlayMakerEnvironmentRepository implements EnvironmentRepository {
	
	private ServiceRepository serviceRepository;
	
	public PlayMakerEnvironmentRepository(ServiceRepository serviceRepository) {
		this.serviceRepository = serviceRepository;
	}
	
	@Override
	public Environment findOne(String application, String profile, String label) {
		Environment env = new Environment(profile, "");
		
		ServiceDetils service = serviceRepository.findByName(application);
		if (service != null) {
			for (ServiceProfile p : service.getProfiles()) {
				if (p.getProfile().getName().equals(profile)) {
					String name = application + "-" + p.getProfile().getName() + ".properties";
					if (p.getProperties() != null) {
						env.add(new PropertySource(name, p.getProperties()));
					}
				}
			}
			
			if (service.getProperties() != null) {
				env.add(new PropertySource(service.getName() + ".properties", service.getProperties()));
			}
		}
		
		return env;
	}

}
