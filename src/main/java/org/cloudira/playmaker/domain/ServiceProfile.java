package org.cloudira.playmaker.domain;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

@Entity
@Table(name = "service_profile")
public class ServiceProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_profile_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "service_id")
	private ServiceDetils service;
	
	@ManyToOne
	@JoinColumn(name = "profile_id")
	private Profile profile;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@MapKeyColumn(name = "property")
	@Column(name = "value")
	@CollectionTable(name = "service_profile_property", joinColumns = @JoinColumn(name = "service_profile_id"))
	private Map<String, String> properties = new HashMap<String, String>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ServiceDetils getService() {
		return service;
	}

	public void setService(ServiceDetils service) {
		this.service = service;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	public Map<String, String> getProperties() {
		return properties;
	}
	
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((service == null) ? 0 : service.hashCode());
		result = prime * result + id;
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
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
		ServiceProfile other = (ServiceProfile) obj;
		if (service == null) {
			if (other.service != null)
				return false;
		} else if (!service.equals(other.service))
			return false;
		if (id != other.id)
			return false;
		if (profile == null) {
			if (other.profile != null)
				return false;
		} else if (!profile.equals(other.profile))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ServiceProfile [id=" + id + ", service=" + service + ", profile=" + profile + "]";
	}
	
}
