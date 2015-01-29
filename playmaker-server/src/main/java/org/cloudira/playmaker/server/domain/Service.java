package org.cloudira.playmaker.server.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "service")
public class Service {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "service")
	private List<ServiceInstance> instances = new ArrayList<ServiceInstance>();
	
	@ElementCollection(fetch = FetchType.EAGER)
	@MapKeyColumn(name = "property")
	@Column(name = "value")
	@CollectionTable(name = "service_property", joinColumns = @JoinColumn(name = "service_id"))
	private Map<String, String> properties = new HashMap<String, String>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "service")
	private List<ServiceProfile> profiles = new ArrayList<ServiceProfile>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<ServiceInstance> getInstances() {
		return instances;
	}
	
	public void setInstances(List<ServiceInstance> instances) {
		this.instances = instances;
	}
	
	public Map<String, String> getProperties() {
		return properties;
	}
	
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	
	public List<ServiceProfile> getProfiles() {
		return profiles;
	}
	
	public void setProfiles(List<ServiceProfile> profiles) {
		this.profiles = profiles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Service other = (Service) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Service [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	
}
