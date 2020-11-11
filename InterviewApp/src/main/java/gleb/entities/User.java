package gleb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class User {

  private long id;

  private String email;

  private String userName;

  private String password;

  private boolean active;

  private List<UserRole> userRoles;

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

  @Column(name = "username", nullable = false)
  public String getUserName() {
    return userName;
  }

  public void setUserName(String surname) {
    this.userName = surname;
  }

  @Column(nullable = false)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Column(name = "active", nullable = false)
  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  @Enumerated(EnumType.STRING)
  @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
  public List<UserRole> getUserRoles() {
    return userRoles;
  }

  public void setUserRoles(List<UserRole> userRoles) {
    this.userRoles = userRoles;
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

  public enum UserRole {USER}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return id == user.id &&
      active == user.active &&
      Objects.equals(email, user.email) &&
      Objects.equals(userName, user.userName) &&
      Objects.equals(password, user.password) &&
      Objects.equals(userRoles, user.userRoles) &&
      Objects.equals(speakedInterviews, user.speakedInterviews) &&
      Objects.equals(developedVacancies, user.developedVacancies);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, userName, password, active, userRoles, speakedInterviews, developedVacancies);
  }

  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", email='" + email + '\'' +
      ", userName='" + userName + '\'' +
      ", password='" + password + '\'' +
      ", active=" + active +
      ", userRoles=" + userRoles +
      ", speakedInterviews=" + speakedInterviews +
      ", developedVacancies=" + developedVacancies +
      '}';
  }
}
