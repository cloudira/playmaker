package org.cloudira.playmaker.server.repository;

import org.cloudira.playmaker.server.domain.ServiceInstance;
import org.springframework.data.repository.CrudRepository;

public interface ServiceInstanceRepository extends CrudRepository<ServiceInstance, Integer> {
	
}
