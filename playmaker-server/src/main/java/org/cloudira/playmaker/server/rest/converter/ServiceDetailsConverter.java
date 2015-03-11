package org.cloudira.playmaker.server.rest.converter;

import org.cloudira.playmaker.server.domain.Service;
import org.cloudira.playmaker.server.rest.resource.ServiceDetailsResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ServiceDetailsConverter implements Converter<Service, ServiceDetailsResource> {
	
	@Override
	public ServiceDetailsResource convert(Service service) {
		if (service == null) {
			return null;
		}
		
		ServiceDetailsResource res = new ServiceDetailsResource();
		res.setId(service.getId());
		res.setName(service.getName());
		res.setDescription(service.getDescription());
		
		return res;
	}

}
