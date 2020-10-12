package org.jpwh.model.associations.onetoone.foreigngenerator;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Address {
	@Id
	@GeneratedValue(generator = "addressKeyGenerator")
	@GenericGenerator(name = "addressKeyGenerator", strategy = "foreign",
			parameters = @Parameter(name = "property", value = "user"))
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

	@OneToOne(optional = false)
	@PrimaryKeyJoinColumn
	protected User user;

	public Address() {
	}

	public Address(@NotNull String street, @NotNull String zipcode, @NotNull String city, User user) {
		this.street = street;
		this.zipcode = zipcode;
		this.city = city;
		this.user = user;
	}

	public Address(User user) {
		this.user = user;
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
