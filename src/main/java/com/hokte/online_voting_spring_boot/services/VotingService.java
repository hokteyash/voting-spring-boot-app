package com.hokte.online_voting_spring_boot.services;

import com.hokte.online_voting_spring_boot.dto.VoteRequestDto;
import com.hokte.online_voting_spring_boot.exception.ResourceNotFoundException;
import com.hokte.online_voting_spring_boot.exception.VoteNotAllowedException;
import com.hokte.online_voting_spring_boot.models.Candidate;
import com.hokte.online_voting_spring_boot.models.Vote;
import com.hokte.online_voting_spring_boot.models.Voter;
import com.hokte.online_voting_spring_boot.repo.CandidateRepo;
import com.hokte.online_voting_spring_boot.repo.VoteRepo;
import com.hokte.online_voting_spring_boot.repo.VoterRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotingService {
    private VoteRepo voteRepo;
    private CandidateRepo candidateRepo;
    private VoterRepo voterRepo;

    public VotingService(VoteRepo voteRepo, CandidateRepo candidateRepo, VoterRepo voterRepo) {
        this.voteRepo = voteRepo;
        this.candidateRepo = candidateRepo;
        this.voterRepo = voterRepo;
    }

    @Transactional
    public Vote castVote(Long voterId, Long candidateId) {
        if(!voterRepo.existsById(voterId)){
            throw new ResourceNotFoundException("Voter with id " + voterId + " not found");
        }
        if(!candidateRepo.existsById(candidateId)){
            throw new ResourceNotFoundException("Candidate with id " + candidateId + " not found");
        }
        Voter voter = voterRepo.findById(voterId).get();
        if(voter.isHasVoted()){
            throw new VoteNotAllowedException("Voter with id " + voterId + " is already voted");
        }
        Candidate candidate = candidateRepo.findById(candidateId).get();
        Vote vote = new Vote();
        vote.setCandidate(candidate);
        vote.setVoter(voter);
        // voteRepo.save(vote);
        candidate.setVoteCount(candidate.getVoteCount() + 1);
        candidateRepo.save(candidate);
        voter.setHasVoted(true);
        voter.setVote(vote);
        voterRepo.save(voter);
        return vote;
    }

    public List<Vote> getAllVoters(){
        return voteRepo.findAll();
    }
}
