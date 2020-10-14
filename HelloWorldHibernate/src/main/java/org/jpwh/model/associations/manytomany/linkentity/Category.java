package org.jpwh.model.associations.manytomany.linkentity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	public long getId() {
		return id;
	}

	protected String name;

	public Category(String name) {
		this.name = name;
	}

	public Category() {
	}

	@OneToMany(mappedBy = "category")
	protected Set<CategorizedItem> categorizedItems = new HashSet<>();

	public Set<CategorizedItem> getCategorizedItems() {
		return categorizedItems;
	}

	public void setCategorizedItems(Set<CategorizedItem> categorizedItems) {
		this.categorizedItems = categorizedItems;
	}
}
