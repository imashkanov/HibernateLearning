package gleb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Entity;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Candidate {
  private long id;

  private String name;

  private String surname;

  private Date birthday;

  private float salaryInDollars;

  private List<Skill> candidateSkills;

  private List<Vacancy> passedVacancies;

  private List<Interview> interviews;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Column(nullable = false)
  public String getName() { return name; }

  public void setName(String name) { this.name = name; }

  @Column(nullable = false)
  public String getSurname() { return surname; }

  public void setSurname(String surname) { this.surname = surname; }

  @Column(nullable = false)
  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  @Column(nullable = false)
  public Float getSalaryInDollars() {
    return salaryInDollars;
  }

  public void setSalaryInDollars(Float salaryInDollars) {
    this.salaryInDollars = salaryInDollars;
  }

  @ManyToMany()
  @JoinTable(name = "candidate_competence",
    joinColumns = {@JoinColumn(name = "candidate_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "skill_id", referencedColumnName = "id")}
    )
  @JsonIgnore
  public List<Skill> getSkills() {
    return candidateSkills;
  }

  public void setSkills(List<Skill> skills) {
    this.candidateSkills = skills;
  }

  @OneToMany(mappedBy = "candidate")
  @JsonIgnore
  public List<Interview> getInterviews() { return interviews; }

  public void setInterviews(List<Interview> interviews) {
    this.interviews = interviews;
  }


  @ManyToMany
  @JoinTable(name = "candidates_for_vacancies",
    joinColumns = {@JoinColumn(name = "candidate_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "vacancy_id", referencedColumnName = "id")}
    )
  @JsonIgnore
  public List<Vacancy> getPassedVacancies() { return passedVacancies; }

  public void setPassedVacancies(List<Vacancy> passedVacancies) { this.passedVacancies = passedVacancies; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Candidate candidate = (Candidate) o;
    return id == candidate.id &&
      Float.compare(candidate.salaryInDollars, salaryInDollars) == 0 &&
      Objects.equals(name, candidate.name) &&
      Objects.equals(surname, candidate.surname) &&
      Objects.equals(birthday, candidate.birthday) &&
      Objects.equals(candidateSkills, candidate.candidateSkills) &&
      Objects.equals(passedVacancies, candidate.passedVacancies) &&
      Objects.equals(interviews, candidate.interviews);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, surname, birthday, salaryInDollars, candidateSkills, passedVacancies, interviews);
  }

  @Override
  public String toString() {
    return "Candidate{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", surname='" + surname + '\'' +
      ", birthday=" + birthday +
      ", salaryInDollars=" + salaryInDollars +
      ", candidateSkills=" + candidateSkills +
      ", passedVacancies=" + passedVacancies +
      ", interviews=" + interviews +
      '}';
  }
}
