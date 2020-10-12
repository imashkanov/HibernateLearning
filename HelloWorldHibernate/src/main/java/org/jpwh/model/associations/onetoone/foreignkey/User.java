package org.jpwh.model.associations.onetoone.foreignkey;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	protected String username;

	@OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
	@JoinColumn(unique = true) //создаёт столбец SHIPPINGADDRESS_ID по умолчанию
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
