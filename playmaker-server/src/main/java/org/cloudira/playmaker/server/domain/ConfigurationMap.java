package org.cloudira.playmaker.server.domain;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("map")
public class ConfigurationMap extends ConfigurationItem {

	@OneToMany(mappedBy = "parent")
	private List<ConfigurationItem> items;

	public List<ConfigurationItem> getItems() {
		return items;
	}

	public void setItems(List<ConfigurationItem> items) {
		this.items = items;
	}
	
}
