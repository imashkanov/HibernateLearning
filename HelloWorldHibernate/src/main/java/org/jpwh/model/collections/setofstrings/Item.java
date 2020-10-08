package org.jpwh.model.collections.setofstrings;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	public long getId() {
		return id;
	}

	@ElementCollection
	@CollectionTable(name = "IMAGE", //по умолчанию имя ITEM_IMAGES
			joinColumns = @JoinColumn(name = "ITEM_ID"))
	@Column(name = "FILENAME")
	protected Set<String> images = new HashSet<>(); //обязательно инициализировать, чтобы избежать проблем с неинициализированными коллекциями
}
