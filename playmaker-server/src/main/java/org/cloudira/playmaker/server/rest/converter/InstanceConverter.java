package org.cloudira.playmaker.server.rest.converter;

import org.cloudira.playmaker.server.domain.ServiceInstance;
import org.cloudira.playmaker.server.rest.resource.InstanceResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class InstanceConverter implements Converter<ServiceInstance, InstanceResource> {

	@Override
	public InstanceResource convert(ServiceInstance serviceInstance) {
		if (serviceInstance == null) {
			return null;
		}
		
		InstanceResource res = new InstanceResource();
		res.setId(serviceInstance.getId());
		res.setName(serviceInstance.getName());
		res.setHost(serviceInstance.getHost());
		res.setPort(serviceInstance.getPort());
		
		return res;
	}

}
