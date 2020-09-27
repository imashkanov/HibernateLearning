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

	//������������ ���, ��������� �� ���������, ��� ��� ��� �� ������ Address
	protected Address homeAddress;

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
}
