package gleb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
public class Interview extends BaseEntity {

  private Candidate candidate;

  private Vacancy vacancy;

  private Timestamp planDate;

  private Timestamp factDate;

  private List<User> speakers;

  @ManyToOne
  @JoinColumn(name = "candidate_id", nullable = false)
  public Candidate getCandidate() {
    return candidate;
  }

  public void setCandidate(Candidate candidate) {
    this.candidate = candidate;
  }

  @ManyToOne
  @JoinColumn(name = "vacancy_id", nullable = false)
  public Vacancy getVacancy() {
    return vacancy;
  }

  public void setVacancy(Vacancy vacancy) {
    this.vacancy = vacancy;
  }

  @Column(nullable = false)
  public Timestamp getPlanDate() {
    return planDate;
  }

  public void setPlanDate(Timestamp planDate) {
    this.planDate = planDate;
  }

  public Timestamp getFactDate() {
    return factDate;
  }

  public void setFactDate(Timestamp factDate) {
    this.factDate = factDate;
  }

  @ManyToMany
  @JoinTable(name = "interview_user",
    joinColumns = {@JoinColumn(name = "interview_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "interviewer_id", referencedColumnName = "id")}
  )
  @JsonIgnore
  public List<User> getSpeakers() { return speakers; }

  public void setSpeakers(List<User> speakers) { this.speakers = speakers; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Interview interview = (Interview) o;
    return Objects.equals(candidate, interview.candidate) &&
            Objects.equals(vacancy, interview.vacancy) &&
            Objects.equals(planDate, interview.planDate) &&
            Objects.equals(factDate, interview.factDate) &&
            Objects.equals(speakers, interview.speakers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(candidate, vacancy, planDate, factDate, speakers);
  }

  @Override
  public String toString() {
    return "Interview{" +
            "candidate=" + candidate +
            ", vacancy=" + vacancy +
            ", planDate=" + planDate +
            ", factDate=" + factDate +
            ", speakers=" + speakers +
            '}';
  }
}
