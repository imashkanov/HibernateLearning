package org.jpwh.model.associations.manytomany.ternary.map;

import javax.persistence.*;

@Entity
@Table(name = "ITEM_TERNARY_MAP")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	public long getId() {
		return id;
	}

	protected String name;

	public Item() {
	}

	public Item(String name) {
		this.name = name;
	}
}
