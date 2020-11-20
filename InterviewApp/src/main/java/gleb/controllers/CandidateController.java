package gleb.controllers;

import gleb.dto.candidates.InsertCandidateRequestDto;
import gleb.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CandidateController {
	private CandidateService candidateService;

	@Autowired
	public void setCandidateService(CandidateService candidateService) {
		this.candidateService = candidateService;
	}

	@PostMapping("/insertCandidate")
	public ResponseEntity insertCandidate(@RequestBody InsertCandidateRequestDto requestDto) {
		return candidateService.insertCandidate(requestDto);
	}
}
