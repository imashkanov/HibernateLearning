package org.jpwh.model.associations.manytomany.linkentity;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "CATEGORY_ITEM")
@Immutable //������������ ��������
public class CategorizedItem {

	@Embeddable
	public static class Id implements Serializable {
		@Column(name = "CATEGORY_ID")
		protected long categoryId;

		@Column(name = "ITEM_ID")
		protected long itemId;

		public Id() {
		}

		public Id(long categoryId, long itemId) {
			this.categoryId = categoryId;
			this.itemId = itemId;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Id id = (Id) o;
			return categoryId == id.categoryId &&
					itemId == id.itemId;
		}

		@Override
		public int hashCode() {
			return Objects.hash(categoryId, itemId);
		}
	}

	@EmbeddedId
	protected Id id = new Id();

	@Column(updatable = false)
	@NotNull
	protected String addedBy;

	@Column(updatable = false)
	@NotNull
	protected Date addedOn;

	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID", insertable = false, updatable = false)
	protected Category category;

	@ManyToOne
	@JoinColumn(name = "ITEM_ID", insertable = false, updatable = false)
	protected Item item;

	public CategorizedItem() {
	}

	public CategorizedItem(String addedBy, Category category, Item item) {
		this.addedBy = addedBy;
		this.category = category;
		this.item = item;

		this.id.categoryId = category.getId(); //�������� ����������� ������
		this.id.itemId = item.getId(); //�������� ����������� ������

		category.getCategorizedItems().add(this); //�������� ����������� ������
		item.getCategorizedItems().add(this); //�������� ����������� ������
	}
}
