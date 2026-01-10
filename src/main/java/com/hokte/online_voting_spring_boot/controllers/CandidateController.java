package com.hokte.online_voting_spring_boot.controllers;

import com.hokte.online_voting_spring_boot.models.Candidate;
import com.hokte.online_voting_spring_boot.services.CandidateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidate")
public class CandidateController {

    CandidateService candidateService;
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping("/getAllCandidates")
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        return new ResponseEntity<>(candidateService.getCandidates(), HttpStatus.OK);
    }

    @GetMapping("/getCandidate/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable long id) {
        return new ResponseEntity<>(candidateService.getCandidateById(id), HttpStatus.OK);
    }

    @PostMapping("/addCandidate")
    public ResponseEntity<Candidate> addCandidate(@RequestBody @Valid Candidate candidate){
        return new ResponseEntity<>(candidateService.addCandidate(candidate),HttpStatus.CREATED);
    }

    @PutMapping("/updateCandidate/{id}")
    public ResponseEntity<Candidate> updateCandidate(@PathVariable long id, @RequestBody Candidate candidate){
        return new ResponseEntity<>(candidateService.updateCandidate(id,candidate),HttpStatus.OK);
    }

    @DeleteMapping("/deleteCandidate/{id}")
    public ResponseEntity<String> deleteCandidate(@PathVariable long id){
        candidateService.deleteCandidate(id);
        return new ResponseEntity<>("Deleted Candidate",HttpStatus.OK);
    }
}
