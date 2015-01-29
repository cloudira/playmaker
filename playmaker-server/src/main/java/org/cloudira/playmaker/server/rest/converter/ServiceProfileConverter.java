package org.cloudira.playmaker.server.rest.converter;

import org.cloudira.playmaker.server.domain.ServiceProfile;
import org.cloudira.playmaker.server.rest.resource.ServiceProfileResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ServiceProfileConverter implements Converter<ServiceProfile, ServiceProfileResource> {

	@Override
	public ServiceProfileResource convert(ServiceProfile source) {
		if (source == null) {
			return null;
		}
		
		ServiceProfileResource resource = new ServiceProfileResource();
		resource.setName(source.getProfile().getName());
		resource.setProperties(source.getProperties());
		
		return resource;
	}

}
