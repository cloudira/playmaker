package org.cloudira.playmaker.server.repository;

import org.cloudira.playmaker.server.domain.Service;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<Service, Integer> {

	public Service findByName(String name);
	
}
