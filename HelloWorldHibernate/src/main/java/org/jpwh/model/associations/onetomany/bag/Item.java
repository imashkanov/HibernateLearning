package org.jpwh.model.associations.onetomany.bag;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	protected String name;

	@OneToMany(mappedBy = "item")
	public Collection<Bid> bids = new ArrayList<>();

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

	public Collection<Bid> getBids() {
		return bids;
	}

	public void setBids(Collection<Bid> bids) {
		this.bids = bids;
	}
}
