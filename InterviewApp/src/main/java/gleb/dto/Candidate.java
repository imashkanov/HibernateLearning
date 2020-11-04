package gleb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Entity;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "candidate", schema = "GlebDB")

public class Candidate extends AbstractEntity {
    private String name;
    private String surname;
    private Date birthday;
    private float salaryInDollars;
    private List<Skill> candidateSkills;
    private List<Vacancy> passedVacancies;
    private List<Interview> interviews;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return super.getId();
    }


    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Basic
    @Column(name = "surname", nullable = true, length = 255)
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    @Basic
    @Column(name = "birthday", nullable = true)
    public Date getBirthday() {
        if (birthday == null) {
            return null;
        }
        return new Date(birthday.getTime());
    }

    public void setBirthday(Date birthday) {
        if (birthday == null) {
            this.birthday = null;
            return;
        }
        this.birthday = new Date(birthday.getTime());
    }

    @Basic
    @Column(name = "salary_in_dollars", nullable = true, precision = 2)
    public Float getSalaryInDollars() {
        return salaryInDollars;
    }
    public void setSalaryInDollars(Float salaryInDollars) {
        this.salaryInDollars = salaryInDollars;
    }

    @ManyToMany()
    @JoinTable(name = "candidate_competence",
      joinColumns = {@JoinColumn(name = "id_candidate", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "id_skill", referencedColumnName = "id")}
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
    @JoinTable(name = "vacancy_candidates",
      joinColumns = {@JoinColumn(name = "id_candidate", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "id_vacancy", referencedColumnName = "id")}
    )
    @JsonIgnore
    public List<Vacancy> getPassedVacancies() { return passedVacancies; }
    public void setPassedVacancies(List<Vacancy> passedVacancies) { this.passedVacancies = passedVacancies; }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return Float.compare(candidate.salaryInDollars, salaryInDollars) == 0 &&
                Objects.equals(name, candidate.name) &&
                Objects.equals(surname, candidate.surname) &&
                Objects.equals(birthday, candidate.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, birthday, salaryInDollars);
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", birthday=" + birthday
                + ", salaryInDollars=" + salaryInDollars + '}';
    }
}
