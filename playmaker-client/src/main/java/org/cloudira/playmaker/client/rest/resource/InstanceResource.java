package org.cloudira.playmaker.client.rest.resource;

import lombok.Data;

@Data
public class InstanceResource {

	private int id;
	private String name;
	private String host;
	private int port;

}
