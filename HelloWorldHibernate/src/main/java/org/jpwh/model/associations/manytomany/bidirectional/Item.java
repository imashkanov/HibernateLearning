package org.jpwh.model.associations.manytomany.bidirectional;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	protected String name;

	public Item() {
	}

	public Item(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	@ManyToMany(mappedBy = "items")
	protected Set<Category> categories = new HashSet<>();

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
}
