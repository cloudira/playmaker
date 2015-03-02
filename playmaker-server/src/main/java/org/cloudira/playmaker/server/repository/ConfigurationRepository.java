package org.cloudira.playmaker.server.repository;

import java.util.List;

import org.cloudira.playmaker.server.domain.ConfigurationItem;
import org.cloudira.playmaker.server.domain.Profile;
import org.cloudira.playmaker.server.domain.Service;
import org.springframework.data.repository.CrudRepository;

public interface ConfigurationRepository extends CrudRepository<ConfigurationItem, Integer> {

	public List<ConfigurationItem> findByProfileAndServiceAndParentIsNull(Profile profile, Service service);
	
}
