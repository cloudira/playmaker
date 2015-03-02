package org.cloudira.playmaker.server.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="ctype", discriminatorType = DiscriminatorType.STRING)
@Table(name = "config")
public abstract class ConfigurationItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "config_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "profile_id")
	private Profile profile;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "service_id")
	private Service service;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "parent_id")
	private ConfigurationMap parent;
	
	@Column(name = "name")
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Profile getProfile() {
		return profile;
	}
	
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public ConfigurationMap getParent() {
		return parent;
	}

	public void setParent(ConfigurationMap parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
