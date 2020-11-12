package gleb.dto;

import gleb.entities.Skill;

import java.util.List;

public class SkillListResponseDto {
	private List<Skill> skills;

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
}
