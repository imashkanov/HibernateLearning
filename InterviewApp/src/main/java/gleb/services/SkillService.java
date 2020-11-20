package gleb.services;

import gleb.dto.skills.DeleteSkillByIdRequestDto;
import gleb.dto.skills.InsertSkillRequestDto;
import gleb.dto.skills.UpdateSkillRequestDto;
import org.springframework.http.ResponseEntity;

public interface SkillService extends AbstractService {
	ResponseEntity getSkills();

	ResponseEntity getCountOfSkills();

	void deleteSkillById(DeleteSkillByIdRequestDto deleteSkillByIdRequestDto);

	void deleteAllSkills();

	ResponseEntity insertSkill(InsertSkillRequestDto insertSkillRequestDto);

	void updateSkill(UpdateSkillRequestDto updateSkillRequestDto);
}
