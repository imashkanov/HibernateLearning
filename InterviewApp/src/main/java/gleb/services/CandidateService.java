package gleb.services;

import gleb.dto.candidates.InsertCandidateRequestDto;
import org.springframework.http.ResponseEntity;

public interface CandidateService extends AbstractService{
	ResponseEntity getCandidates();

	ResponseEntity getCountOfCandidates();

//	void deleteCandidateById(DeleteSkillByIdRequestDto deleteCandidateByIdRequestDto);

	void deleteAllCandidates();

	ResponseEntity insertCandidate(InsertCandidateRequestDto insertCandidateRequestDto);

//	void updateCandidate(UpdateSkillRequestDto updateCandidateRequestDto);
}
