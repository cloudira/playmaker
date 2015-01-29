package org.cloudira.playmaker.server.rest.converter;

import java.util.List;

import org.cloudira.playmaker.server.domain.Service;
import org.cloudira.playmaker.server.rest.resource.ServiceDetailsResource;
import org.cloudira.playmaker.server.rest.resource.ServiceProfileResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ServiceDetailsConverter implements Converter<Service, ServiceDetailsResource> {
	
	@Autowired
	private RestConversionService conversionService;
	
	@SuppressWarnings("unchecked")
	@Override
	public ServiceDetailsResource convert(Service service) {
		if (service == null) {
			return null;
		}
		
		ServiceDetailsResource res = new ServiceDetailsResource();
		res.setId(service.getId());
		res.setName(service.getName());
		res.setDescription(service.getDescription());
		res.setProperties(service.getProperties()); 
		res.setProfiles(conversionService.convertCollection(service.getProfiles(), List.class, ServiceProfileResource.class));
		
		return res;
	}

}
