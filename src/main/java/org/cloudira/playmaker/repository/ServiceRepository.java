package org.cloudira.playmaker.repository;

import org.cloudira.playmaker.domain.ServiceDetils;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<ServiceDetils, Integer> {

	public ServiceDetils findByName(String name);
	
}
