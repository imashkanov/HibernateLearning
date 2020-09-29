package org.jpwh.model.simple;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable //встраиваемый класс (пользовательский тип), в отличие от сущности не имеет идентичности
// отсутствует ID
public class Address {
	@NotNull
	@Column(nullable = false) //используется при генерации DDL
	protected String street;

	protected City city;

	public Address() {
		// Hibernate сперва вызывает конструктор по умолчанию, а потом setter's
	}

	public Address(String street) {
		this.street = street;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
}
