package org.jpwh.model.associations.manytomany.ternary.map;

import javax.persistence.*;

@Entity
@Table(name = "USER_TERNARY_MAP")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	public long getId() {
		return id;
	}

	protected String name;

	public User() {
	}

	public User(String name) {
		this.name = name;
	}
}
