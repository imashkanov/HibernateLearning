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

	@ElementCollection //������������ ���������!
	@CollectionTable(name = "IMAGE") //������� �� ��������� ���������� �� ITEM_IMAGES
	protected Map<Filename, Image> images = new HashMap<>(); //��������� MapKeyColumn � AttributeOverrides ����� �� ��������������, ����� ���� ����� ������ ������������ �����
}
