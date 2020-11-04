package gleb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class User {

  private long id;

  private String email;

  private String password;

  private String surname;

  private String name;

  private State state;

  private List<Interview> speakedInterviews;

  private List<Vacancy> developedVacancies;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Column(nullable = false)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Column(nullable = false)
  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  @Column(nullable = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "user_state", nullable = false)
  @Enumerated(EnumType.STRING)
  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  @ManyToMany
  @JoinTable(name = "interview_feedback",
    joinColumns = {@JoinColumn(name = "interviewer_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "interview_id", referencedColumnName = "id")}
  )
  @JsonIgnore
  public List<Interview> getSpeakedInterviews() { return speakedInterviews; }

  public void setSpeakedInterviews(List<Interview> speakedInterviews) { this.speakedInterviews = speakedInterviews; }

  @OneToMany(mappedBy = "user")
  @JsonIgnore
  public List<Vacancy> getDevelopedVacancies() { return developedVacancies; }

  public void setDevelopedVacancies(List<Vacancy> developedVacancies) { this.developedVacancies = developedVacancies; }

  public enum State {BLOCKED,ACTIVE}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return id == user.id &&
      Objects.equals(email, user.email) &&
      Objects.equals(password, user.password) &&
      Objects.equals(surname, user.surname) &&
      Objects.equals(name, user.name) &&
      state == user.state &&
      Objects.equals(speakedInterviews, user.speakedInterviews) &&
      Objects.equals(developedVacancies, user.developedVacancies);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, password, surname, name, state, speakedInterviews, developedVacancies);
  }

  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", email='" + email + '\'' +
      ", password='" + password + '\'' +
      ", surname='" + surname + '\'' +
      ", name='" + name + '\'' +
      ", state=" + state +
      ", speakedInterviews=" + speakedInterviews +
      ", developedVacancies=" + developedVacancies +
      '}';
  }
}
