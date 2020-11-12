package gleb.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Skill extends BaseEntity {

  private String name;

  private List<Vacancy> correspondingVacancies;

  private List<Candidate> correspondingCandidates;

  @Column(nullable = false)
  public String getName() { return name; }

  public void setName(String name) { this.name = name; }

  @ManyToMany
  @JoinTable(name = "vacancy_requirement",
    joinColumns = {@JoinColumn(name = "skill_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "vacancy_id", referencedColumnName = "id")}
  )
  public List<Vacancy> getCorrespondingVacancies() { return correspondingVacancies; }

  public void setCorrespondingVacancies(List<Vacancy> correspondingVacancies) {
    this.correspondingVacancies = correspondingVacancies;
  }

  @ManyToMany
  @JoinTable(name = "candidate_competence",
    joinColumns = {@JoinColumn(name = "skill_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "candidate_id", referencedColumnName = "id")}
  )

  public List<Candidate> getCorrespondingCandidates() { return correspondingCandidates; }

  public void setCorrespondingCandidates(List<Candidate> correspondingCandidates) {
    this.correspondingCandidates = correspondingCandidates;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Skill skill = (Skill) o;
    return Objects.equals(name, skill.name) &&
            Objects.equals(correspondingVacancies, skill.correspondingVacancies) &&
            Objects.equals(correspondingCandidates, skill.correspondingCandidates);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, correspondingVacancies, correspondingCandidates);
  }

  @Override
  public String toString() {
    return "Skill{" +
            "name='" + name + '\'' +
            ", correspondingVacancies=" + correspondingVacancies +
            ", correspondingCandidates=" + correspondingCandidates +
            '}';
  }
}
