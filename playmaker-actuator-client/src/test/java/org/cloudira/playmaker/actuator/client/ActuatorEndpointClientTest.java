package org.cloudira.playmaker.actuator.client;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.cloudira.playmaker.actuator.client.resource.EnvironmentResource;
import org.cloudira.playmaker.actuator.client.resource.JMXDomainsResource;
import org.cloudira.playmaker.actuator.client.resource.JMXResource;
import org.cloudira.playmaker.actuator.client.resource.MetricsResource;
import org.cloudira.playmaker.actuator.client.resource.ThreadInfoResource;
import org.cloudira.playmaker.actuator.client.resource.TraceResource;
import org.cloudira.playmaker.actuator.client.resource.JMXDomainsResource.JMXDomainResource;
import org.cloudira.playmaker.actuator.client.resource.JMXDomainsResource.JMXDomainTypeResource;
import org.jolokia.client.exception.J4pException;

public class ActuatorEndpointClientTest {

	public static void main(String[] args) throws J4pException {
		String managementUrl = "http://localhost:8888/admin";
		{
			ActuatorEndpointClient<?> client = new AutoConfigurationEndpointClient();
			System.out.println(client.invoke(managementUrl));
		}
		{
			ConfigurationPropertiesEndpointClient client = new ConfigurationPropertiesEndpointClient();
			System.out.println(client.invoke(managementUrl));

		}
		{
			DumpEndpointClient client = new DumpEndpointClient();
			List<ThreadInfoResource> invoke = (List<ThreadInfoResource>) client.invoke(managementUrl);
			for (ThreadInfoResource res : invoke) {
				System.out.println(res.getThreadName());
			}
		}
		{
			TraceEndpointClient client = new TraceEndpointClient();
			List<TraceResource> invoke = client.invoke(managementUrl);
			for (TraceResource tr : invoke) {
				System.out.println(tr);
			}
		}
		{
			HealthEndpointClient client = new HealthEndpointClient();
			System.out.println(client.invoke(managementUrl));
		}
		{
			InfoEndpointClient client = new InfoEndpointClient();
			System.out.println(client.invoke(managementUrl));
		}
		{
			MappingsEndpointClient client = new MappingsEndpointClient();
			System.out.println(client.invoke(managementUrl));
		}
		{
			MetricsEndpointClient client = new MetricsEndpointClient();
			System.out.println(client.invoke(managementUrl));
			System.out.println(client.invoke(managementUrl, MetricsResource.class));
			System.out.println(client.getValue(managementUrl, "mem"));
		}
		{
			BeansEndpointClient client = new BeansEndpointClient();
			System.out.println(client.invoke(managementUrl));
		}
		{
			JolokiaEndpointClient ec = new JolokiaEndpointClient();
	        JMXResource list = ec.getList(managementUrl);
	        if (list instanceof JMXDomainsResource) {
	        	for (Map.Entry<String, JMXDomainResource> entry : ((JMXDomainsResource) list).getDomains().entrySet()) {
	        		System.out.println(entry);
	        	}
	        }
	        list = ec.getList(managementUrl, "JMImplementation");
	        if (list instanceof JMXDomainResource) {
	        	for (Map.Entry<String, JMXDomainTypeResource> entry : ((JMXDomainResource) list).getTypes().entrySet()) {
	        		System.out.println(entry);
	        	}
	        }
	        list = ec.getList(managementUrl, "JMImplementation", "type=MBeanServerDelegate");
	        if (list instanceof JMXDomainResource) {
	        	for (Map.Entry<String, JMXDomainTypeResource> entry : ((JMXDomainResource) list).getTypes().entrySet()) {
	        		System.out.println(entry);
	        	}
	        }
	        list = ec.getList(managementUrl, "JMImplementation", "type=MBeanServerDelegate");
	        if (list instanceof JMXDomainResource) {
	        	for (Map.Entry<String, JMXDomainTypeResource> entry : ((JMXDomainResource) list).getTypes().entrySet()) {
	        		System.out.println(entry);
	        	}
	        }
		}
		{
			EnvironmentEndpointClient client = new EnvironmentEndpointClient();
			System.out.println(client.invoke(managementUrl));
			System.out.println(client.invoke(managementUrl, EnvironmentResource.class));
			System.out.println(client.getValue(managementUrl, "os.version"));
			System.out.println(client.reset(managementUrl));
			// System.out.println(client.getValue("XXXX"));
			System.out.println(client.set(managementUrl, Collections.<String, String> singletonMap("XXXX", "CCCCC")));
			System.out.println(client.getValue(managementUrl, "XXXX"));
			System.out.println(client.reset(managementUrl));
		}
		{
			PauseEndpointClient client = new PauseEndpointClient();
			System.out.println(client.invoke(managementUrl));
		}
		{
			ResumeEndpointClient client = new ResumeEndpointClient();
			System.out.println(client.invoke(managementUrl));
		}
		{
			RefreshEndpointClient client = new RefreshEndpointClient();
			System.out.println(client.invoke(managementUrl));
		}
		{
			RestartEndpointClient client = new RestartEndpointClient();
			System.out.println(client.invoke(managementUrl));
		}
		{
			ShutdownEndpointClient client = new ShutdownEndpointClient();
			System.out.println(client.invoke(managementUrl));
		}
	}
}
