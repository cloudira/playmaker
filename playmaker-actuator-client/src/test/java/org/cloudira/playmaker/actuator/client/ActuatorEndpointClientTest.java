package org.cloudira.playmaker.actuator.client;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.cloudira.playmaker.actuator.client.resource.Environment;
import org.cloudira.playmaker.actuator.client.resource.JMXDomains;
import org.cloudira.playmaker.actuator.client.resource.JMX;
import org.cloudira.playmaker.actuator.client.resource.Metrics;
import org.cloudira.playmaker.actuator.client.resource.ThreadInfo;
import org.cloudira.playmaker.actuator.client.resource.Trace;
import org.cloudira.playmaker.actuator.client.resource.JMXDomains.JMXDomain;
import org.cloudira.playmaker.actuator.client.resource.JMXDomains.JMXDomainType;
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
			List<ThreadInfo> invoke = (List<ThreadInfo>) client.invoke(managementUrl);
			for (ThreadInfo res : invoke) {
				System.out.println(res.getThreadName());
			}
		}
		{
			TraceEndpointClient client = new TraceEndpointClient();
			List<Trace> invoke = client.invoke(managementUrl);
			for (Trace tr : invoke) {
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
			System.out.println(client.invoke(managementUrl, Metrics.class));
			System.out.println(client.getValue(managementUrl, "mem"));
		}
		{
			BeansEndpointClient client = new BeansEndpointClient();
			System.out.println(client.invoke(managementUrl));
		}
		{
			EnvironmentEndpointClient client = new EnvironmentEndpointClient();
			System.out.println(client.invoke(managementUrl));
			System.out.println(client.invoke(managementUrl, Environment.class));
			System.out.println(client.getValue(managementUrl, "os.version"));
			System.out.println(client.reset(managementUrl));
			// System.out.println(client.getValue("XXXX"));
			System.out.println(client.set(managementUrl, Collections.<String, String> singletonMap("XXXX", "CCCCC")));
			System.out.println(client.getValue(managementUrl, "XXXX"));
			System.out.println(client.reset(managementUrl));
		}
		{
			JolokiaEndpointClient ec = new JolokiaEndpointClient();
	        JMX list = ec.getList(managementUrl);
	        if (list instanceof JMXDomains) {
	        	for (Map.Entry<String, JMXDomain> entry : ((JMXDomains) list).getDomains().entrySet()) {
	        		System.out.println(entry);
	        	}
	        }
	        list = ec.getList(managementUrl, "JMImplementation");
	        if (list instanceof JMXDomain) {
	        	for (Map.Entry<String, JMXDomainType> entry : ((JMXDomain) list).getTypes().entrySet()) {
	        		System.out.println(entry);
	        	}
	        }
	        list = ec.getList(managementUrl, "JMImplementation", "type=MBeanServerDelegate");
	        if (list instanceof JMXDomain) {
	        	for (Map.Entry<String, JMXDomainType> entry : ((JMXDomain) list).getTypes().entrySet()) {
	        		System.out.println(entry);
	        	}
	        }
	        list = ec.getList(managementUrl, "JMImplementation", "type=MBeanServerDelegate");
	        if (list instanceof JMXDomain) {
	        	for (Map.Entry<String, JMXDomainType> entry : ((JMXDomain) list).getTypes().entrySet()) {
	        		System.out.println(entry);
	        	}
	        }
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
