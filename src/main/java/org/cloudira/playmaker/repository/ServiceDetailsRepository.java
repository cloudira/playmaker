package org.cloudira.playmaker.repository;

import org.cloudira.playmaker.domain.ServiceDetails;
import org.springframework.data.repository.CrudRepository;

public interface ServiceDetailsRepository extends CrudRepository<ServiceDetails, Integer> {

	public ServiceDetails findByName(String name);
	
}
