package org.jpwh.model.complexschemas.compositekey.mapsid;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class User {
  @EmbeddedId
  protected UserId id;

  @ManyToOne
  @MapsId("departmentId")
  protected Department department;

  public User() {
  }

  public User(UserId id) {
    this.id = id;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", department=" + department +
      '}';
  }
}
