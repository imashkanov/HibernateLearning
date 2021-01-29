package gleb.services.impl;

import gleb.dto.candidates.InsertCandidateRequestDto;
import gleb.dto.candidates.InsertCandidateResponseDto;
import gleb.entities.Candidate;
import gleb.mapper.candidate.CandidateDtoMapper;
import gleb.repositories.CandidateRepository;
import gleb.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CandidateServiceImpl implements CandidateService {

	private CandidateRepository candidateRepository;
	private CandidateDtoMapper candidateDtoMapper;

	@Autowired
	public void setCandidateRepository(CandidateRepository candidateRepository) {
		this.candidateRepository = candidateRepository;
	}

	@Autowired
	public void setCandidateUtils(CandidateDtoMapper candidateDtoMapper) {
		this.candidateDtoMapper = candidateDtoMapper;
	}

	@Override
	public ResponseEntity getCandidates() {
		return null;
	}

	@Override
	public ResponseEntity getCountOfCandidates() {
		return null;
	}

	@Override
	public void deleteAllCandidates() {

	}

	@Override
	public ResponseEntity insertCandidate(InsertCandidateRequestDto insertCandidateRequestDto) {
		InsertCandidateResponseDto outModel = new InsertCandidateResponseDto();
		Candidate insertingCandidate = candidateDtoMapper.fillCandidateFromDto(insertCandidateRequestDto);
		try {
			Candidate insertedCandidate = candidateRepository.save(insertingCandidate);
			outModel.setId(insertedCandidate.getId());
			return ResponseEntity.ok(outModel);
		} catch (Exception e) {
			return getResponseEntityOnServerError(e);
		}
	}
}
