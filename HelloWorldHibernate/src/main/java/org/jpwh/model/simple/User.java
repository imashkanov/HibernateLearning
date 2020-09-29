package org.jpwh.model.simple;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USERS")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	public long getId() {
		return id;
	}

	//встраиваемый тип, аннотации не требуются, так как она на классе Address
	protected Address homeAddress;

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	@AttributeOverrides({
			@AttributeOverride(name = "street",
					column = @Column(name = "BILLING_STREET")),
			@AttributeOverride(name = "zipcode",
					column = @Column(name = "BILLING_ZIPCODE")),
			@AttributeOverride(name = "street",
					column = @Column(name = "BILLING_CITY"))
	})
	protected Address billingAddress;

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}
}
