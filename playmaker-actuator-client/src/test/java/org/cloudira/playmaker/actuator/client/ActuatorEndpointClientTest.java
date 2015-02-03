package org.cloudira.playmaker.actuator.client;

import java.util.Collections;
import java.util.List;

import org.cloudira.playmaker.actuator.client.resource.EnvironmentResource;
import org.cloudira.playmaker.actuator.client.resource.MetricsResource;
import org.cloudira.playmaker.actuator.client.resource.ThreadInfoResource;
import org.cloudira.playmaker.actuator.client.resource.TraceResource;

public class ActuatorEndpointClientTest {

	public static void main(String[] args) {
		{
			ActuatorEndpointClient<?> client = new AutoConfigurationEndpointClient("http://localhost:8888/admin");
			System.out.println(client.invoke());
		}
		{
			ConfigurationPropertiesEndpointClient client = new ConfigurationPropertiesEndpointClient("http://localhost:8888/admin");
			System.out.println(client.invoke());

		}
		{
			DumpEndpointClient client = new DumpEndpointClient("http://localhost:8888/admin");
			List<ThreadInfoResource> invoke = (List<ThreadInfoResource>) client.invoke();
			for (ThreadInfoResource res : invoke) {
				System.out.println(res.getThreadName());
			}
		}
		{
			TraceEndpointClient client = new TraceEndpointClient("http://localhost:8888/admin");
			List<TraceResource> invoke = client.invoke();
			for (TraceResource tr : invoke) {
				System.out.println(tr);
			}
		}
		{
			HealthEndpointClient client = new HealthEndpointClient("http://localhost:8888/admin");
			System.out.println(client.invoke());
		}
		{
			InfoEndpointClient client = new InfoEndpointClient("http://localhost:8888/admin");
			System.out.println(client.invoke());
		}
		{
			MappingsEndpointClient client = new MappingsEndpointClient("http://localhost:8888/admin");
			System.out.println(client.invoke());
		}
		{
			MetricsEndpointClient client = new MetricsEndpointClient("http://localhost:8888/admin");
			System.out.println(client.invoke());
			System.out.println(client.invoke(MetricsResource.class));
			System.out.println(client.getValue("mem"));
		}
		{
			BeansEndpointClient client = new BeansEndpointClient("http://localhost:8888/admin");
			System.out.println(client.invoke());
		}
		{
			EnvironmentEndpointClient client = new EnvironmentEndpointClient("http://localhost:8888/admin");
			System.out.println(client.invoke());
			System.out.println(client.invoke(EnvironmentResource.class));
			System.out.println(client.getValue("os.version"));
			System.out.println(client.reset());
			// System.out.println(client.getValue("XXXX"));
			System.out.println(client.set(Collections.<String, String> singletonMap("XXXX", "CCCCC")));
			System.out.println(client.getValue("XXXX"));
			System.out.println(client.reset());
		}
		{
			PauseEndpointClient client = new PauseEndpointClient("http://localhost:8888/admin");
			System.out.println(client.invoke());
		}
		{
			ResumeEndpointClient client = new ResumeEndpointClient("http://localhost:8888/admin");
			System.out.println(client.invoke());
		}
		{
			RefreshEndpointClient client = new RefreshEndpointClient("http://localhost:8888/admin");
			System.out.println(client.invoke());
		}
		{
			RestartEndpointClient client = new RestartEndpointClient("http://localhost:8888/admin");
			System.out.println(client.invoke());
		}
		{
			ShutdownEndpointClient client = new ShutdownEndpointClient("http://localhost:8888/admin");
			System.out.println(client.invoke());
		}
	}
}
