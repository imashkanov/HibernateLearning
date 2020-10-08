package org.jpwh.model.collections.mapofstrings;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

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
	@MapKeyColumn(name = "FILENAME")
	@Column(name = "IMAGENAME")
	protected Map<String, String> images = new HashMap<>(); //обязательно инициализировать, чтобы избежать проблем с неинициализированными коллекциями
}
