package gleb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "interview", schema = "GlebDB")

public class Interview extends AbstractEntity {
    private Candidate candidate;
    private Vacancy vacancy;
    private Timestamp planDate;
    private Timestamp factDate;
    private List<User> speakers;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return super.getId();
    }

    @ManyToOne
    @JoinColumn(name = "id_candidate", referencedColumnName = "id", nullable = false)
    public Candidate getCandidate() {
        return candidate;
    }
    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    @ManyToOne
    @JoinColumn(name = "id_vacancy", referencedColumnName = "id", nullable = false)
    public Vacancy getVacancy() {
        return vacancy;
    }
    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    @Basic
    @Column(name = "plan_date")
    public Timestamp getPlanDate() {
        if (planDate == null) {
            return null;
        }
        return new Timestamp(planDate.getTime());
    }

    public void setPlanDate(Timestamp planDate) {
        if (planDate == null) {
            this.planDate = null;
            return;
        }
        this.planDate = new Timestamp(planDate.getTime());
    }

    @Basic
    @Column(name = "fact_date")
    public Timestamp getFactDate() {
        if (factDate == null) {
            return null;
        }
        return new Timestamp(factDate.getTime());
    }

    public void setFactDate(Timestamp factDate) {
        if (factDate == null) {
            this.factDate = null;
            return;
        }
        this.factDate = new Timestamp(factDate.getTime());
    }

    @ManyToMany
    @JoinTable(name = "interview_feedback",
      joinColumns = {@JoinColumn(name = "id_interview", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "id_interviewer", referencedColumnName = "id")}
    )
    @JsonIgnore
    public List<User> getSpeakers() { return speakers; }
    public void setSpeakers(List<User> speakers) { this.speakers = speakers; }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interview interview = (Interview) o;
        return Objects.equals(candidate, interview.candidate) &&
                Objects.equals(vacancy, interview.vacancy) &&
                Objects.equals(planDate, interview.planDate) &&
                Objects.equals(factDate, interview.factDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidate, vacancy, planDate, factDate);
    }

    @Override
    public String toString() {
        return "Interview{" +
                "candidate=" + candidate +
                ", vacancy=" + vacancy +
                ", planDate=" + planDate +
                ", factDate=" + factDate +
                '}';
    }
}
