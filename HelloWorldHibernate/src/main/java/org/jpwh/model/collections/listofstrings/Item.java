package org.jpwh.model.collections.listofstrings;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	public long getId() {
		return id;
	}

	@ElementCollection
	@OrderColumn //позволяет сохранить порядок, имя по умолчанию: IMAGES_ORDER
	@CollectionTable(name = "IMAGE", //по умолчанию имя ITEM_IMAGES
			joinColumns = @JoinColumn(name = "ITEM_ID"))
	@Column(name = "FILENAME")
	protected List<String> images = new ArrayList<>(); //обязательно инициализировать, чтобы избежать проблем с неинициализированными коллекциями
}
