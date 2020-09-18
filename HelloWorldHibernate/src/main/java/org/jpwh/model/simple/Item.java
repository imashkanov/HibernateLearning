package org.jpwh.model.simple;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  public Item(long id) {
    this.id = id;
  }

  public Item() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
