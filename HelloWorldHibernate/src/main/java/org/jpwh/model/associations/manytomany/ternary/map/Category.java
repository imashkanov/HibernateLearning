package org.jpwh.model.associations.manytomany.ternary.map;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "CATEGORY_TERNARY_MAP")
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

  @ManyToMany(cascade = CascadeType.PERSIST)
  @MapKeyJoinColumn(name = "ITEM_ID")
  @JoinTable(name = "CATEGORY_ITEM_TERNARY_MAP",
          joinColumns = @JoinColumn(name = "CATEGORY_ID"),
          inverseJoinColumns = @JoinColumn(name = "USER_ID"))
  protected Map<Item, User> itemAddedBy = new HashMap<>();

  public Map<Item, User> getItemAddedBy() {
    return itemAddedBy;
  }
}
