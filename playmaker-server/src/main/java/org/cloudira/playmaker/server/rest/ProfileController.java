package org.cloudira.playmaker.server.rest;

import java.util.ArrayList;
import java.util.List;

import org.cloudira.playmaker.server.domain.Profile;
import org.cloudira.playmaker.server.repository.ProfileRepository;
import org.cloudira.playmaker.server.rest.converter.ProfileConverter;
import org.cloudira.playmaker.server.rest.resource.ProfileResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private ProfileConverter profileConverter;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<ProfileResource> findProfiles() {
		List<ProfileResource> list = new ArrayList<ProfileResource>();
		
		for (Profile profile : profileRepository.findAll()) {
			list.add(profileConverter.convert(profile));
		}
		
		return list;
	}
	
}
