package org.jpwh.model.collections.setofembeddables;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Item {
	@Id
	protected long id;

	public long getId() {
		return id;
	}

	@ElementCollection //обязательная аннотация!
	@CollectionTable(name = "IMAGE") //таблица по умолчанию называлась бы ITEM_IMAGES
	@AttributeOverride(name = "filename", column = @Column(name = "FNAME", nullable = false))
	protected Set<Image> images = new HashSet<>();
}
