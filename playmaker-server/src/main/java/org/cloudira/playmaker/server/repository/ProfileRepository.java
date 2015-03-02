package org.cloudira.playmaker.server.repository;

import org.cloudira.playmaker.server.domain.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Integer> {

	public Profile findByName(String name);
	
}
