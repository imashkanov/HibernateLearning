package gleb.dto.candidates;

import java.sql.Date;

public class InsertCandidateRequestDto {
	private String name;

	private String surname;

	private Date birthday;

	private float salaryInDollars;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public float getSalaryInDollars() {
		return salaryInDollars;
	}

	public void setSalaryInDollars(float salaryInDollars) {
		this.salaryInDollars = salaryInDollars;
	}
}
