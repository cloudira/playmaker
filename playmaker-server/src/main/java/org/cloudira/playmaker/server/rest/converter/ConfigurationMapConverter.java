package org.cloudira.playmaker.server.rest.converter;

import org.cloudira.playmaker.server.domain.ConfigurationMap;
import org.cloudira.playmaker.server.rest.resource.ConfigurationMapResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationMapConverter implements Converter<ConfigurationMap, ConfigurationMapResource> {

	@Override
	public ConfigurationMapResource convert(ConfigurationMap map) {
		if (map != null) {
			ConfigurationMapResource res = new ConfigurationMapResource();
			res.setId(map.getId());
			res.setName(map.getName());
			
			return res;
		}
		
		return null;
	}

}
