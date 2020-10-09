package org.jpwh.model.associations.onetomany.bidirectional;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	@Column(nullable = false)
	protected String name;

	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY) //������������ ��� �����������������, ��� ���� ��� ���������� ������� ���� � ������ Bid
	                                                      //�������� ������������
	protected Set<Bid> bids = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Bid> getBids() {
		return bids;
	}

	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}
}
