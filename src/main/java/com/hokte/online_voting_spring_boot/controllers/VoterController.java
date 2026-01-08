package com.hokte.online_voting_spring_boot.controllers;

import com.hokte.online_voting_spring_boot.models.Voter;
import com.hokte.online_voting_spring_boot.services.VoterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voter")
public class VoterController {

    private VoterService voterService;
    public VoterController(VoterService voterService) {
        this.voterService = voterService;
    }

    @PostMapping("/register")
    public ResponseEntity<Voter> addVoter(@RequestBody @Valid Voter voter) {
        return new ResponseEntity<>(voterService.registerVoter(voter), HttpStatus.CREATED);
    }

    @GetMapping("/allVoters")
    public ResponseEntity<List<Voter>> getAllVoters() {
        return new ResponseEntity<>(voterService.getAllVoters(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voter> getVoterById(@PathVariable long id) {
        return new ResponseEntity<>(voterService.getVoterById(id), HttpStatus.OK);
    }

    @PutMapping("/updateVoter/{id}")
    public ResponseEntity<Voter> updateVoter(@PathVariable long id, @RequestBody Voter voter) {
        return new ResponseEntity<>(voterService.updateVoter(id, voter), HttpStatus.OK);
    }

    @DeleteMapping("/deleteVoter/{id}")
    public ResponseEntity<String> deleteVoter(@PathVariable long id) {
        voterService.deleteVoter(id);
        return new ResponseEntity<>("Voter with id "+id+" deleted successfully",HttpStatus.OK);
    }
}
