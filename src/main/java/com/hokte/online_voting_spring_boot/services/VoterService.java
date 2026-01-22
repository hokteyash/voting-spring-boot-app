package com.hokte.online_voting_spring_boot.services;

import com.hokte.online_voting_spring_boot.exception.DuplicateResourceException;
import com.hokte.online_voting_spring_boot.exception.ResourceNotFoundException;
import com.hokte.online_voting_spring_boot.models.Candidate;
import com.hokte.online_voting_spring_boot.models.Vote;
import com.hokte.online_voting_spring_boot.models.Voter;
import com.hokte.online_voting_spring_boot.repo.CandidateRepo;
import com.hokte.online_voting_spring_boot.repo.VoterRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoterService {

    private final VoterRepo voterRepo;
    private final CandidateRepo candidateRepo;

    public VoterService(VoterRepo voterRepo, CandidateRepo candidateRepo) {
        this.voterRepo = voterRepo;
        this.candidateRepo = candidateRepo;
    }

    public Voter registerVoter(Voter voter) {
        if(voterRepo.existsByEmail(voter.getEmail())){
            throw new DuplicateResourceException("Voter already registered with "+voter.getEmail()+" email address");
        }
        voter.setHasVoted(false);
        return voterRepo.save(voter);
    }

    public List<Voter> getAllVoters() {
        return voterRepo.findAll();
    }

    public Voter getVoterById(long id) {
        Voter voter = voterRepo.findById(id).orElse(null);
        if(voter == null){
            throw new ResourceNotFoundException("Voter with id "+id+" not found");
        }
        return voter;
    }

    public Voter updateVoter(long id, Voter updated) {
        Voter voter = voterRepo.findById(id).orElse(null);
        if(voter == null){
            throw new ResourceNotFoundException("Voter with id "+id+" not found");
        }
        if(updated.getEmail()==null && updated.getName()==null)
            throw new RuntimeException("Please provide something to update");
        if(updated.getEmail()!=null)
            voter.setEmail(updated.getEmail());
        if(updated.getName()!=null)
            voter.setName(updated.getName());
        return voterRepo.save(voter);
    }

    @Transactional
    public void deleteVoter(long id) {
        Voter voter = voterRepo.findById(id).orElse(null);
        if(voter == null){
            throw new ResourceNotFoundException("Voter with id "+id+" not found");
        }
        Vote vote = voter.getVote();
        if(vote != null){
            Candidate candidate = vote.getCandidate();
            candidate.setVoteCount(candidate.getVoteCount()-1);
            candidateRepo.save(candidate);
        }
        voterRepo.delete(voter);
    }
}
