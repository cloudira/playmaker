package org.cloudira.playmaker.server.rest.converter;

import org.cloudira.playmaker.server.domain.ConfigurationProperty;
import org.cloudira.playmaker.server.rest.resource.ConfigurationPropertyResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationPropertyConverter implements Converter<ConfigurationProperty, ConfigurationPropertyResource> {

	@Override
	public ConfigurationPropertyResource convert(ConfigurationProperty prop) {
		if (prop != null) {
			ConfigurationPropertyResource res = new ConfigurationPropertyResource();
			res.setId(prop.getId());
			res.setName(prop.getName());
			res.setValue(prop.getValue());
			
			return res;
		}
		
		return null;
	}

}
