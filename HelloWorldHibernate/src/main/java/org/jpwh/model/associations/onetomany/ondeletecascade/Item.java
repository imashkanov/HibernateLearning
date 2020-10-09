package org.jpwh.model.associations.onetomany.ondeletecascade;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY) //используется для двунаправленности, mappedBy - это типа имя переменной данного типа в классе Bid
	                                                      //является обязательной
	@OnDelete(action = OnDeleteAction.CASCADE) //двунаправленная связь - только около OneToMany

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
