package gleb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;
import java.util.Objects;

@Entity
public class Vacancy {
  private long id;

  private String position;

  private float salaryInDollarsFrom;

  private float salaryInDollarsTo;

  private VacancyState vacancyState;

  private float experienceYearsRequire;

  private User user;

  private List<Interview> iterviews;

  private List<Candidate> candidates;

  private List<Skill> skillRequirements;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Column(nullable = false)
  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  @Column(nullable = false)
  public float getSalaryInDollarsFrom() {
    return salaryInDollarsFrom;
  }

  public void setSalaryInDollarsFrom(float salaryInDollarsFrom) {
    this.salaryInDollarsFrom = salaryInDollarsFrom;
  }

  @Column(nullable = false)
  public float getSalaryInDollarsTo() {
    return salaryInDollarsTo;
  }

  public void setSalaryInDollarsTo(float salaryInDollarsTo) {
    this.salaryInDollarsTo = salaryInDollarsTo;
  }

  @Column(name = "vacancy_state", nullable = false)
  @Enumerated(EnumType.STRING)
  public VacancyState getVacancyState() {
    return vacancyState;
  }

  public void setVacancyState(VacancyState vacancyState) {
    this.vacancyState = vacancyState;
  }

  @Column(nullable = false)
  public float getExperienceYearsRequire() {
    return experienceYearsRequire;
  }
  public void setExperienceYearsRequire(float experienceYearsRequire) {
    this.experienceYearsRequire = experienceYearsRequire;
  }

  @ManyToOne
  @JoinColumn(name = "developer_id", referencedColumnName = "id", nullable = false)
  public User getUser() { return user; }

  public void setUser(User developer) { this.user = developer; }

  @OneToMany(mappedBy = "vacancy")
  @JsonIgnore
  public List<Interview> getIterviews() { return iterviews; }
  public void setIterviews(List<Interview> interviews) { this.iterviews = interviews; }


  @ManyToMany
  @JoinTable(name = "candidates_for_vacancies",
    joinColumns = {@JoinColumn(name = "vacancy_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "candidate_id", referencedColumnName = "id")}
  )
  @JsonIgnore
  public List<Candidate> getCandidates() { return candidates; }

  public void setCandidates(List<Candidate> passedCandidates) { this.candidates = passedCandidates; }

  @ManyToMany
  @JoinTable(name = "vacancy_requirement",
    joinColumns = {@JoinColumn(name = "id_vacancy", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "id_skill", referencedColumnName = "id")}
  )
  @JsonIgnore
  public List<Skill> getSkillRequirements() { return skillRequirements; }

  public void setSkillRequirements(List<Skill> skillRequirements) { this.skillRequirements = skillRequirements; }

  public enum VacancyState {OPEN, CLOSE}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Vacancy vacancy = (Vacancy) o;
    return id == vacancy.id &&
      Float.compare(vacancy.salaryInDollarsFrom, salaryInDollarsFrom) == 0 &&
      Float.compare(vacancy.salaryInDollarsTo, salaryInDollarsTo) == 0 &&
      Float.compare(vacancy.experienceYearsRequire, experienceYearsRequire) == 0 &&
      Objects.equals(position, vacancy.position) &&
      vacancyState == vacancy.vacancyState &&
      Objects.equals(user, vacancy.user) &&
      Objects.equals(iterviews, vacancy.iterviews) &&
      Objects.equals(candidates, vacancy.candidates) &&
      Objects.equals(skillRequirements, vacancy.skillRequirements);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, position, salaryInDollarsFrom, salaryInDollarsTo, vacancyState, experienceYearsRequire, user, iterviews, candidates, skillRequirements);
  }

  @Override
  public String toString() {
    return "Vacancy{" +
      "id=" + id +
      ", position='" + position + '\'' +
      ", salaryInDollarsFrom=" + salaryInDollarsFrom +
      ", salaryInDollarsTo=" + salaryInDollarsTo +
      ", vacancyState=" + vacancyState +
      ", experienceYearsRequire=" + experienceYearsRequire +
      ", user=" + user +
      ", iterviews=" + iterviews +
      ", candidates=" + candidates +
      ", skillRequirements=" + skillRequirements +
      '}';
  }
}