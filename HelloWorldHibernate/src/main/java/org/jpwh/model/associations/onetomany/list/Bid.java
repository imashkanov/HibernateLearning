package org.jpwh.model.associations.onetomany.list;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Bid {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	protected BigDecimal value;

	@ManyToOne
	@JoinColumn(name = "ITEM_ID", insertable = false, updatable = false)
	protected Item item;

	public Bid() {
	}

	public Bid(BigDecimal value, Item item) {
		this.value = value;
		this.item = item;
	}

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
