package org.jpwh.model.associations.onetoone.foreignkey;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	public long getId() {
		return id;
	}

	@NotNull
	protected String street;
	@NotNull
	protected String zipcode;
	@NotNull
	protected String city;

	public Address() {
	}

	public Address(@NotNull String street, @NotNull String zipcode, @NotNull String city) {
		this.street = street;
		this.zipcode = zipcode;
		this.city = city;
	}

	@Override
	public String toString() {
		return "Address{" +
				"id=" + id +
				", street='" + street + '\'' +
				", zipcode='" + zipcode + '\'' +
				", city='" + city + '\'' +
				'}';
	}
}
