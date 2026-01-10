package com.hokte.online_voting_spring_boot.services;

import com.hokte.online_voting_spring_boot.exception.ResourceNotFoundException;
import com.hokte.online_voting_spring_boot.models.Candidate;
import com.hokte.online_voting_spring_boot.models.Vote;
import com.hokte.online_voting_spring_boot.repo.CandidateRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {

    CandidateRepo candidateRepo;
    public CandidateService(CandidateRepo candidateRepo) {
        this.candidateRepo = candidateRepo;
    }

    public List<Candidate> getCandidates() {
        return candidateRepo.findAll();
    }

    public Candidate getCandidateById(long id) {
        Candidate candidate = candidateRepo.findById(id).orElse(null);
        if(candidate == null){
            throw new ResourceNotFoundException("Candidate with id " + id + " found");
        }
        return candidate;
    }

    public Candidate addCandidate(Candidate candidate) {
        return candidateRepo.save(candidate);
    }

    public Candidate updateCandidate(long id, Candidate updatedCandidate) {
        Candidate candidate = candidateRepo.findById(id).orElse(null);
        if(candidate == null){
            throw new ResourceNotFoundException("Candidate with id " + id + "not found");
        }
        if(updatedCandidate.getParty() == null && updatedCandidate.getName() == null)
            throw new RuntimeException("Give some data atleast to update");

        if(updatedCandidate.getName() != null)
            candidate.setName(updatedCandidate.getName());
        if (updatedCandidate.getParty() != null)
            candidate.setParty(updatedCandidate.getParty());
        return candidateRepo.save(candidate);
    }

    public void deleteCandidate(long id) {
        Candidate candidate = candidateRepo.findById(id).orElse(null);
        if(candidate == null){
            throw new ResourceNotFoundException("Candidate with id " + id + "not found");
        }
        List<Vote> votes = candidate.getVote();
        for (Vote vote : votes) {
            vote.setCandidate(null);
        }
        candidate.getVote().clear();
        candidateRepo.delete(candidate);
    }
}
