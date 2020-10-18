package org.jpwh.model.complexschemas.compositekey.readonly;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserId implements Serializable {
  protected String username;
  protected Long departmentId;

  public UserId() {
  }

  public UserId(String username, Long departmentId) {
    this.username = username;
    this.departmentId = departmentId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserId userId = (UserId) o;
    return Objects.equals(username, userId.username) &&
      Objects.equals(departmentId, userId.departmentId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, departmentId);
  }

  @Override
  public String toString() {
    return "UserId{" +
      "username='" + username + '\'' +
      ", departmentId=" + departmentId +
      '}';
  }
}
