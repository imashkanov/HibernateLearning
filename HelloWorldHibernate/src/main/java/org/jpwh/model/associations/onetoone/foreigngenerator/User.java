package org.jpwh.model.associations.onetoone.foreigngenerator;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	protected String username;

	@OneToOne(mappedBy = "user", cascade = CascadeType.PERSIST)
	protected Address shippingAddress;

	public User(String username) {
		this.username = username;
	}

	public User() {
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
}
