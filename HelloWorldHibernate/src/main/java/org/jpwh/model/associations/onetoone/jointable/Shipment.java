package org.jpwh.model.associations.onetoone.jointable;

import javax.persistence.*;

@Entity
public class Shipment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	public long getId() {
		return id;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "ITEM_SHIPMENT", //��� ��������� �������
	        joinColumns = @JoinColumn(name = "SHIMPENT_ID"), // ��������������� ����� id-��� Shipment
			inverseJoinColumns = @JoinColumn(name = "ITEM_ID")) //�������������� ����� id-��� Item
	protected Item item;

	public Shipment() {
	}

	public Shipment(Item item) {
		this.item = item;
	}
}
