package org.jpwh.model.associations.manytomany.ternary;

import javax.persistence.*;
import java.util.Date;

@Embeddable
public class CategorizedItem {
  @ManyToOne
  @JoinColumn(name = "ITEM_ID", nullable = false, updatable = false) // в состав первчичного ключа войдёт то, где nullable == false, то есть USER_ID не войдёт
  protected Item item; //ругается - пофиг

  @ManyToOne
  @JoinColumn(name = "USER_ID", updatable = false) // не будет в составе первичного ключа, так как мб null
  protected User addedBy;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(updatable = false)
  protected Date addedOn  = new Date();

  public CategorizedItem() {
  }

  public CategorizedItem(Item item, User addedBy) {
    this.item = item;
    this.addedBy = addedBy;
  }
}
