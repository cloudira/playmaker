package org.cloudira.playmaker.server.rest.converter;

import java.util.ArrayList;
import java.util.List;

import org.cloudira.playmaker.server.domain.Service;
import org.cloudira.playmaker.server.domain.ServiceProfile;
import org.cloudira.playmaker.server.rest.resource.ServiceDetailsResource;
import org.cloudira.playmaker.server.rest.resource.ServiceProfileResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ServiceDetailsConverter implements Converter<Service, ServiceDetailsResource> {

	@Override
	public ServiceDetailsResource convert(Service service) {
		ServiceDetailsResource res = new ServiceDetailsResource();
		res.setId(service.getId());
		res.setName(service.getName());
		res.setDescription(service.getDescription());
		res.setProperties(service.getProperties());
		
		List<ServiceProfileResource> profiles = new ArrayList<ServiceProfileResource>();
		
		for (ServiceProfile profile : service.getProfiles()) {
			ServiceProfileResource p = new ServiceProfileResource();
			p.setName(profile.getProfile().getName());
			p.setProperties(profile.getProperties());
			
			profiles.add(p);
		}
		
		res.setProfiles(profiles);
		
		return res;
	}

}
