package org.cloudira.playmaker.sample.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableDiscoveryClient
public class SampleApplication implements CommandLineRunner {
	
	private static Logger log = LoggerFactory.getLogger(SampleApplication.class);
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	public static void main(String[] args) {
		SpringApplication.run(SampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.debug("Local instance: {}", discoveryClient.getLocalServiceInstance());
		log.debug("All services: {}", discoveryClient.getServices());
		log.debug("All instances: {}", discoveryClient.getAllInstances());
		log.debug("All instances of local service: {}", discoveryClient.getInstances(discoveryClient.getLocalServiceInstance().getServiceId()));
	}

}