package gleb.services;

import gleb.requestmodels.DeleteSkillByIdInModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public interface SkillService {

	default ResponseEntity getResponseEntityOnServerError(Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON_UTF8)
				.body("Server error");
	}

	ResponseEntity getSkills();

	ResponseEntity getCountOfSkills();

	void deleteSkillById(DeleteSkillByIdInModel inModel);
}
