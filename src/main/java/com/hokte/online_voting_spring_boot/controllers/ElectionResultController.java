package com.hokte.online_voting_spring_boot.controllers;
import com.hokte.online_voting_spring_boot.dto.ElectionResultRequestDto;
import com.hokte.online_voting_spring_boot.dto.ElectionResultResponseDto;
import com.hokte.online_voting_spring_boot.models.ElectionResult;
import com.hokte.online_voting_spring_boot.repo.ElectionResultRepo;
import com.hokte.online_voting_spring_boot.services.ElectionResultService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/election-result")
@CrossOrigin
public class ElectionResultController {
    private ElectionResultService electionResultService;

    public ElectionResultController(ElectionResultService electionResultService) {
        this.electionResultService = electionResultService;
    }

    @PostMapping("/declare")
    public ResponseEntity<ElectionResultResponseDto> declareElectionResult(@RequestBody @Valid ElectionResultRequestDto electionResultRequestDto){
        ElectionResult electionResult = electionResultService.getElectionResult(electionResultRequestDto.getElectionName());
        ElectionResultResponseDto electionResultResponseDto = new ElectionResultResponseDto();
        electionResultResponseDto.setElectionName(electionResult.getElectionName());
        electionResultResponseDto.setWinnerId(electionResult.getWinnerId());
        electionResultResponseDto.setTotalVotes(electionResult.getTotalVotes());
        electionResultResponseDto.setWinnerVotes(electionResult.getWinner().getVoteCount());
        return ResponseEntity.ok(electionResultResponseDto);
    }

    @GetMapping("/allElectionResult")
    public ResponseEntity<List<ElectionResult>> getAllElectionResult(){
        List<ElectionResult> electionResults = electionResultService.getAllElectionResult();
        return ResponseEntity.ok(electionResults);
    }
}
