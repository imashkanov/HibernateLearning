package gleb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class User extends BaseEntity {

  private String userName;

  private String firstName;

  private String lastName;

  private String password;

  private String email;

  private List<Role> roles;

  private List<Interview> speakedInterviews;

  private List<Vacancy> developedVacancies;

  @Column(name = "username", nullable = false)
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Column(name = "firstname", nullable = false)
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstname) {
    this.firstName = firstname;
  }

  @Column(name = "lastname", nullable = false)
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastname) {
    this.lastName = lastname;
  }

  @Column(nullable = false)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_roles",
          joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
          inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
  )
  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  @ManyToMany
  @JoinTable(name = "interview_user",
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

}
