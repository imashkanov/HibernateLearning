package gleb.dto;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "skill", schema = "GlebDB")

public class Skill extends AbstractEntity {

    private String name;
    private List<Vacancy> correspondingVacancies;
    private List<Candidate> correspondingCandidates;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return super.getId();
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @ManyToMany
    @JoinTable(name = "vacancy_requirement",
      joinColumns = {@JoinColumn(name = "id_skill", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "id_vacancy", referencedColumnName = "id")}
    )
    
    public List<Vacancy> getCorrespondingVacancies() { return correspondingVacancies; }
    public void setCorrespondingVacancies(List<Vacancy> correspondingVacancies) {
        this.correspondingVacancies = correspondingVacancies;
    }


    @ManyToMany
    @JoinTable(name = "candidate_competence",
      joinColumns = {@JoinColumn(name = "id_skill", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "id_candidate", referencedColumnName = "id")}
    )

    public List<Candidate> getCorrespondingCandidates() { return correspondingCandidates; }
    public void setCorrespondingCandidates(List<Candidate> correspondingCandidates) {
        this.correspondingCandidates = correspondingCandidates;
    }

    @Override
    public final boolean equals(Object o) {
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
                '}';
    }
}
