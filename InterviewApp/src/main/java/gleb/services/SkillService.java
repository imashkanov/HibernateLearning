package gleb.services;

import gleb.dto.DeleteSkillByIdRequestDto;
import gleb.dto.InsertSkillRequestDto;
import gleb.dto.UpdateSkillRequestDto;
import org.springframework.http.ResponseEntity;

public interface SkillService extends AbstractService {
	ResponseEntity getSkills();

	ResponseEntity getCountOfSkills();

	void deleteSkillById(DeleteSkillByIdRequestDto deleteSkillByIdRequestDto);

	void deleteAllSkills();

	ResponseEntity insertSkill(InsertSkillRequestDto insertSkillRequestDto);

	void updateSkill(UpdateSkillRequestDto updateSkillRequestDto);
}
