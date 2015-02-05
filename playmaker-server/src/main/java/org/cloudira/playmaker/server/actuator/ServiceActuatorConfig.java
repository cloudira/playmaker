package org.cloudira.playmaker.server.actuator;

import org.cloudira.playmaker.actuator.client.BeansEndpointClient;
import org.cloudira.playmaker.actuator.client.EnvironmentEndpointClient;
import org.cloudira.playmaker.actuator.client.HealthEndpointClient;
import org.cloudira.playmaker.actuator.client.InfoEndpointClient;
import org.cloudira.playmaker.actuator.client.MetricsEndpointClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceActuatorConfig {

	@Bean
	public InfoEndpointClient infoEndpointClient() {
		return new InfoEndpointClient();
	}
	
	@Bean
	public HealthEndpointClient healthEndpointClient() {
		return new HealthEndpointClient();
	}
	
	@Bean
	public MetricsEndpointClient metricsEndpointClient() {
		return new MetricsEndpointClient();
	}
	
	@Bean
	public EnvironmentEndpointClient environmentEndpointClient() {
		return new EnvironmentEndpointClient();
	}
	
	@Bean
	public BeansEndpointClient beansEndpointClient() {
		return new BeansEndpointClient();
	}
	
}
