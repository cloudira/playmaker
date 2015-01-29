package org.cloudira.playmaker.server.rest.converter;

import org.cloudira.playmaker.server.domain.Profile;
import org.cloudira.playmaker.server.rest.resource.ProfileResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProfileConverter implements Converter<Profile, ProfileResource> {

	@Override
	public ProfileResource convert(Profile profile) {
		ProfileResource res = new ProfileResource();
		res.setId(profile.getId());
		res.setName(profile.getName());
		res.setDescription(profile.getDescription());
		
		return res;
	}

}
