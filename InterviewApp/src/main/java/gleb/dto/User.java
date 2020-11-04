package gleb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Entity;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "GlebDB")

public class User extends AbstractEntity {

    private String email;
    private String password;
    private String surname;
    private String name;
    private State state;
    private List<Interview> speakedInterviews;
    private List<Vacancy> developedVacancies;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return super.getId();
    }

    @Basic
    @Column(name = "email", nullable = false, length = 100, unique = true)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "user_state")
    @Enumerated(EnumType.STRING)
    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }

    @ManyToMany
    @JoinTable(name = "interview_feedback",
      joinColumns = {@JoinColumn(name = "id_interviewer", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "id_interview", referencedColumnName = "id")}
    )
    @JsonIgnore
    public List<Interview> getSpeakedInterviews() { return speakedInterviews; }
    public void setSpeakedInterviews(List<Interview> speakedInterviews) { this.speakedInterviews = speakedInterviews; }

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    public List<Vacancy> getDevelopedVacancies() { return developedVacancies; }
    public void setDevelopedVacancies(List<Vacancy> developedVacancies) { this.developedVacancies = developedVacancies; }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(name, user.name) &&
                state == user.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, surname, name, state);
    }

    public enum State {BLOCKED,ACTIVE}

    @Override
    public String toString() {
        return "User{" +
               "email='" + email + '\'' +
               ", password='" + password + '\'' +
               ", surname='" + surname + '\'' +
               ", name='" + name + '\'' +
               ", state=" + state +
               ", speakedInterviews=" + speakedInterviews +
               ", developedVacancies=" + developedVacancies +
               '}';
    }
}
