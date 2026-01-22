package com.hokte.online_voting_spring_boot.controllers;

import com.hokte.online_voting_spring_boot.dto.VoteRequestDto;
import com.hokte.online_voting_spring_boot.dto.VoteResponseDto;
import com.hokte.online_voting_spring_boot.models.Vote;
import com.hokte.online_voting_spring_boot.services.VotingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voting")
@CrossOrigin
public class VotingController {
    private VotingService votingService;

    public VotingController(VotingService votingService) {
        this.votingService = votingService;
    }

    @GetMapping("/allVotes")
    public ResponseEntity<List<Vote>> getAllVoters(){
        return  new ResponseEntity<>(votingService.getAllVotes(),HttpStatus.OK);
    }

    @PostMapping("/castVote")
    public ResponseEntity<VoteResponseDto> castVote(@RequestBody @Valid VoteRequestDto voteRequestDto){
        Vote vote = votingService.castVote(voteRequestDto.getVoterId(), voteRequestDto.getCandidateId());
        VoteResponseDto voteResponseDto = new VoteResponseDto("Vote casted successfully",true,vote.getCandidateId(),vote.getVoterId());
        return new ResponseEntity<>(voteResponseDto, HttpStatus.CREATED);
    }
}
