package org.jpwh.model.associations.manytomany.ternary;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CATEGORY_TERNARY")
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected long id;

  public long getId() {
    return id;
  }

  protected String name;

  public String getName() {
    return name;
  }

  public Category() {
  }

  public Category(String name) {
    this.name = name;
  }

  @ElementCollection
  @CollectionTable(name = "CATEGORY_ITEM_TERNARY", joinColumns = @JoinColumn(name = "CATEGORY_ID"))
  protected Set<CategorizedItem> categorizedItems = new HashSet<>();

  public Set<CategorizedItem> getCategorizedItems() {
    return categorizedItems;
  }
}
