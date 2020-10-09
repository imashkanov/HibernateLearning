package org.jpwh.model.associations.onetomany.bidirectional;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Bid {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	@Column(nullable = false)
	protected BigDecimal value;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ITEM_ID", nullable = false) //столбец внешнего ключа в таблице Bid, тут ещё можно задать имя этого столбца и ограничения в схему (например как тут not null), тут этой
	                                                //аннотации может не быть
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
