package org.cloudira.playmaker;

import org.cloudira.playmaker.repository.ServiceDetailsRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.config.server.EnvironmentRepository;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class PlayMakerApplication {
	
	@Bean(name = "EnvironmentRepository")
	public EnvironmentRepository environmentRepository(ServiceDetailsRepository serviceRepository) {
		return new PlayMakerEnvironmentRepository(serviceRepository);
	}

	public static void main(String[] args) {
		SpringApplication.run(PlayMakerApplication.class, args);
	}

}
