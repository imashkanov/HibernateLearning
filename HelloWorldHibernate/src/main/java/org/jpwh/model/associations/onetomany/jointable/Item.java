package org.jpwh.model.associations.onetomany.jointable;

import javax.persistence.*;

@Entity
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected long id;

  public long getId() {
    return id;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinTable(name = "ITEM_BUYER", joinColumns = @JoinColumn(name = "ITEM_ID"),
                                  inverseJoinColumns = @JoinColumn(nullable = false))
  protected User buyer;
}
