package org.jpwh.model.associations.onetomany.cascadepersist;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Bid {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	@Column(nullable = false)
	protected BigDecimal value;

	@ManyToOne(fetch = FetchType.LAZY) // ��� cascade ����� �� ���������
	@JoinColumn(name = "ITEM_ID", nullable = false) //������� �������� ����� � ������� Bid, ��� ��� ����� ������ ��� ����� ������� � ����������� � ����� (�������� ��� ��� not null), ��� ����
	                                                //��������� ����� �� ����
	protected Item item;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
}
