package org.jpwh.model.associations.onetomany.list;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

@Entity
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	protected String name;

	@OneToMany
	@JoinColumn(name = "ITEM_ID", nullable = false)
	@OrderColumn(name = "BID_POS", nullable = false)
	public List<Bid> bids = new ArrayList<>();

	public Item() {
	}

	public Item(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}
}
