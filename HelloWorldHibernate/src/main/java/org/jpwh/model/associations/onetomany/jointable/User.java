package org.jpwh.model.associations.onetomany.jointable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected long id;

  public long getId() {
    return id;
  }

  @OneToMany(mappedBy = "buyer")
  protected Set<Item> boughtItems = new HashSet<>();
}
