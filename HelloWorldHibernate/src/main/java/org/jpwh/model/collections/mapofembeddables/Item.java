package org.jpwh.model.collections.mapofembeddables;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
	protected Map<Filename, Image> images = new HashMap<>(); //аннотации MapKeyColumn и AttributeOverrides могут не использоваться, когда роль ключа играет встраиваемый класс
}
