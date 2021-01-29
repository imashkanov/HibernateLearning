package gleb.mapper.candidate;

import gleb.dto.candidates.InsertCandidateRequestDto;
import gleb.entities.Candidate;
import gleb.entities.EntityStatus;
import org.springframework.stereotype.Component;

@Component
public class CandidateDtoMapper {
	public Candidate fillCandidateFromDto(InsertCandidateRequestDto insertCandidateRequestDto) {
		Candidate candidate = new Candidate();
		candidate.setName(insertCandidateRequestDto.getName());
		candidate.setSurname(insertCandidateRequestDto.getSurname());
		candidate.setBirthday(insertCandidateRequestDto.getBirthday());
		candidate.setSalaryInDollars(insertCandidateRequestDto.getSalaryInDollars());
		candidate.setStatus(EntityStatus.ACTIVE);
		return candidate;
	}
}
