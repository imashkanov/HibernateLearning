package org.jpwh.model.helloworld;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // каждый класс хранимой сущности должен иметь хотя бы одну Entity
public class Message {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) //значение будет генерироваться автоматически
  private Long id; //идентификационный аттрибут, должен быть обязательно

  public Long getId() {
    return id;
  }

  private String text;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return "Message{" +
      "id=" + id +
      ", text='" + text + '\'' +
      '}';
  }
}
