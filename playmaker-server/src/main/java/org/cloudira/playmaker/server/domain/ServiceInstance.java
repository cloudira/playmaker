package org.cloudira.playmaker.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "service_instance")
public class ServiceInstance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "instance_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "service_id")
	private Service service;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "host")
	private String host;
	
	@Column(name = "port")
	private int port;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Service getService() {
		return service;
	}
	
	public void setService(Service service) {
		this.service = service;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
}
