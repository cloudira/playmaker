package org.cloudira.playmaker.server.rest;

import java.util.List;

import org.cloudira.playmaker.server.domain.Profile;
import org.cloudira.playmaker.server.repository.ProfileRepository;
import org.cloudira.playmaker.server.rest.converter.RestConversionService;
import org.cloudira.playmaker.server.rest.resource.ProfileResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private RestConversionService conversionService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<ProfileResource> findProfiles() {
		return (List<ProfileResource>) conversionService.convert(profileRepository.findAll(), TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(ProfileResource.class)));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ProfileResource createProfile(@RequestBody ProfileResource res) {
		Profile profile = new Profile();
		profile.setName(res.getName());
		profile.setDescription(res.getDescription());
		
		profile = profileRepository.save(profile);
		
		return conversionService.convert(profile, ProfileResource.class);
	}
	
}
