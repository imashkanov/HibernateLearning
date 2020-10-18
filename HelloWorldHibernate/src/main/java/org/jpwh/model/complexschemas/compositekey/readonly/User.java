package org.jpwh.model.complexschemas.compositekey.readonly;

import javax.persistence.*;

@Entity
public class User {
  @EmbeddedId
  protected UserId id;

  @ManyToOne        // в name должно быть имя соответствующего столбца из id (в userId это departmentId -> должно тут называться DEPARTMENTID)
  @JoinColumn(name = "DEPARTMENTID", insertable = false, updatable = false) //set department не должно быть
  protected Department department;

  public User() {
  }

  public User(String userName, Department department) {
    if (department.getId() == null) { //гарантирует целостность
      throw new IllegalArgumentException("Department is transient");
    }
    this.id = new UserId(userName, department.getId());
    this.department = department;
  }

  public User(UserId id) {
    this.id = id;
  }

  public Department getDepartment() {
    return department;
  }

  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", department=" + department +
      '}';
  }
}
