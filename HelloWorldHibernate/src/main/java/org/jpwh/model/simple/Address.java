package org.jpwh.model.simple;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Embeddable //������������ ����� (���������������� ���), � ������� �� �������� �� ����� ������������
// ����������� ID
public class Address {
	@NotNull
	@Column(nullable = false) //������������ ��� ��������� DDL
	protected String street;

	@NotNull
	@Column(nullable = false, length = 5) //�������������� VARCHAR(255)
	protected String zipcode;

	@NotNull
	@Column(nullable = false)
	protected String city;

	public Address() {
		// Hibernate ������ �������� ����������� �� ���������, � ����� setter's
	}

	public Address(String street, String zipcode, String city) {
		this.street = street;
		this.zipcode = zipcode;
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public boolean equals(Object o) { // ������ �� ������� ������
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Address address = (Address) o;
		return Objects.equals(street, address.street) &&
				Objects.equals(zipcode, address.zipcode) &&
				Objects.equals(city, address.city);
	}

	@Override
	public int hashCode() { // ������ �� ������� ������
		return Objects.hash(street, zipcode, city);
	}
}
