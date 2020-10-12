package org.jpwh.model.associations.onetoone.sharedprimarykey;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User {
	@Id
	protected long id;

	protected String username;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@PrimaryKeyJoinColumn //Стратегияч отображения с общим PK, тут однонаправленная один-к-одному с общим PK от User к Address
	protected Address shippingAddress;

	public User(long id, String username) {
		this.id = id;
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
