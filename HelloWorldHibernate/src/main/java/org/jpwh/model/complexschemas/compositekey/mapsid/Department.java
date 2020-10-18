package org.jpwh.model.complexschemas.compositekey.mapsid;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Department {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected long id;

  protected String name;

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Department() {
  }

  public Department(String name) {
    this.name = name;
  }

  @OneToMany(mappedBy = "department")
  protected Set<User> users = new HashSet<>();

  public Set<User> getUsers() {
    return users;
  }

  public void setUsers(Set<User> users) {
    this.users = users;
  }

  @Override
  public String toString() {
    return "Department{" +
      "id=" + id +
      ", name='" + name + '\'' +
      '}';
  }
}
