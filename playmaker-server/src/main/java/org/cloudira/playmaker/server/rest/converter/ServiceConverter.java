package org.cloudira.playmaker.server.rest.converter;

import org.cloudira.playmaker.server.domain.Service;
import org.cloudira.playmaker.server.rest.resource.ServiceResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ServiceConverter implements Converter<Service, ServiceResource> {

	@Override
	public ServiceResource convert(Service service) {
		ServiceResource res = new ServiceResource();
		res.setId(service.getId());
		res.setName(service.getName());
		res.setDescription(service.getDescription());
		
		return res;
	}

}
