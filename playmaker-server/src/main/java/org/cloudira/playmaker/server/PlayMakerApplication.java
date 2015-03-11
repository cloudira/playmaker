package org.cloudira.playmaker.server;

import org.cloudira.playmaker.server.repository.ConfigurationRepository;
import org.cloudira.playmaker.server.repository.ProfileRepository;
import org.cloudira.playmaker.server.repository.ServiceRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.config.server.EnvironmentRepository;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableConfigServer
public class PlayMakerApplication {
	
	@Bean
	public EnvironmentRepository environmentRepository(ServiceRepository serviceRepository, ProfileRepository profileRepository, ConfigurationRepository configurationRepository) {
		return new PlayMakerEnvironmentRepository(serviceRepository, profileRepository, configurationRepository);
	}

	public static void main(String[] args) {
		SpringApplication.run(PlayMakerApplication.class, args);
	}

}
