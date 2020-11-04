package gleb.dto;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public abstract class AbstractEntity implements Serializable, Cloneable {
  private long id;

  public AbstractEntity() {
  }

  public AbstractEntity(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
