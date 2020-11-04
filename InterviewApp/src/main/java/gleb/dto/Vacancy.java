package gleb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "vacancy", schema = "GlebDB")

public class Vacancy extends AbstractEntity {

    private String position;
    private float salaryInDollarsFrom;
    private float salaryInDollarsTo;
    private vacancyState vacancyState;
    private float experienceYearsRequire;
    private User user;
    private List<Interview> interviews;
    private List<Candidate> passedCandidates;
    private List<Skill> skillRequirements;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return super.getId();
    }

    @Basic
    @Column(name = "position", nullable = false, length = 1000)
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    @Basic
    @Column(name = "salary_in_dollars_from",  precision = 2)
    public float getSalaryInDollarsFrom() {
        return salaryInDollarsFrom;
    }
    public void setSalaryInDollarsFrom(float salaryInDollarsFrom) {
        this.salaryInDollarsFrom = salaryInDollarsFrom;
    }

    @Basic
    @Column(name = "salary_in_dollars_to", precision = 2)
    public float getSalaryInDollarsTo() {
        return salaryInDollarsTo;
    }
    public void setSalaryInDollarsTo(float salaryInDollarsTo) {
        this.salaryInDollarsTo = salaryInDollarsTo;
    }

    @Basic
    @Column(name = "vacancy_state")
    @Enumerated(EnumType.STRING)
    public vacancyState getVacancyState() {
        return vacancyState;
    }
    public void setVacancyState(vacancyState vacancyState) {
        this.vacancyState = vacancyState;
    }

    @Basic
    @Column(name = "experience_years_require", precision = 2)
    public float getExperienceYearsRequire() {
        return experienceYearsRequire;
    }
    public void setExperienceYearsRequire(float experienceYearsRequire) {
        this.experienceYearsRequire = experienceYearsRequire;
    }

    @ManyToOne
    @JoinColumn(name = "id_developer", referencedColumnName = "id", nullable = false)
    public User getUser() { return user; }
    public void setUser(User developer) { this.user = developer; }

    @OneToMany(mappedBy = "vacancy")
    @JsonIgnore
    public List<Interview> getInterviews() { return interviews; }
    public void setInterviews(List<Interview> interviews) { this.interviews = interviews; }


    @ManyToMany
    @JoinTable(name = "vacancy_candidates",
      joinColumns = {@JoinColumn(name = "id_vacancy", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "id_candidate", referencedColumnName = "id")}
    )
    @JsonIgnore
    public List<Candidate> getPassedCandidates() { return passedCandidates; }
    public void setPassedCandidates(List<Candidate> passedCandidates) { this.passedCandidates = passedCandidates; }

    @ManyToMany
    @JoinTable(name = "vacancy_requirement",
      joinColumns = {@JoinColumn(name = "id_vacancy", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "id_skill", referencedColumnName = "id")}
    )


    @JsonIgnore
    public List<Skill> getSkillRequirements() { return skillRequirements; }
    public void setSkillRequirements(List<Skill> skillRequirements) { this.skillRequirements = skillRequirements; }


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return Float.compare(vacancy.salaryInDollarsFrom, salaryInDollarsFrom) == 0 &&
                Float.compare(vacancy.salaryInDollarsTo, salaryInDollarsTo) == 0 &&
                Float.compare(vacancy.experienceYearsRequire, experienceYearsRequire) == 0 &&
                Objects.equals(position, vacancy.position) &&
                vacancyState == vacancy.vacancyState &&
                Objects.equals(user, vacancy.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, salaryInDollarsFrom, salaryInDollarsTo, vacancyState, experienceYearsRequire, user);
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "position='" + position + '\'' +
                ", salaryInDollarsFrom=" + salaryInDollarsFrom +
                ", salaryInDollarsTo=" + salaryInDollarsTo +
                ", vacancyState=" + vacancyState +
                ", experienceYearsRequire=" + experienceYearsRequire +
                ", user=" + user +
                '}';
    }

    public enum vacancyState {OPEN, CLOSE}
}
