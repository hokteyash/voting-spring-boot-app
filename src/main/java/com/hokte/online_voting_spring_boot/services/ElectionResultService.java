package com.hokte.online_voting_spring_boot.services;

import com.hokte.online_voting_spring_boot.exception.ResourceNotFoundException;
import com.hokte.online_voting_spring_boot.models.Candidate;
import com.hokte.online_voting_spring_boot.models.ElectionResult;
import com.hokte.online_voting_spring_boot.repo.CandidateRepo;
import com.hokte.online_voting_spring_boot.repo.ElectionResultRepo;
import com.hokte.online_voting_spring_boot.repo.VoteRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElectionResultService {

    private CandidateRepo candidateRepo;
    private VoteRepo voteRepo;
    private ElectionResultRepo electionResultRepo;

    // Data Injection (DI)
    public ElectionResultService(CandidateRepo candidateRepo, VoteRepo voteRepo, ElectionResultRepo electionResultRepo) {
        this.candidateRepo = candidateRepo;
        this.voteRepo = voteRepo;
        this.electionResultRepo = electionResultRepo;
    }

    public ElectionResult getElectionResult(String electionName){
        Optional<ElectionResult> existingElectionResult = electionResultRepo.findByElectionName(electionName);
        // if the election result is already conducted then we will simply return the result
        if(existingElectionResult.isPresent()){
            return existingElectionResult.get();
        }
        if(voteRepo.count() == 0){
            throw new IllegalStateException("Cannot declare the result since no votes are there");
        }
        List<Candidate>allCandidates = candidateRepo.findAllByOrderByVoteCountDesc();
        if(allCandidates.isEmpty()){
            throw new ResourceNotFoundException("No candidates found");
        }
        // Since the list is coming in the format where most voted person will be on top
        // that's why we are picking up the topmost guy as our winner
        Candidate winner = allCandidates.get(0);
        int totalVotes = 0;
        for(Candidate candidate : allCandidates) totalVotes+=(candidate.getVoteCount());
        ElectionResult electionResult = new ElectionResult();
        electionResult.setElectionName(electionName);
        electionResult.setWinner(winner);
        electionResult.setTotalVotes(totalVotes);
        return electionResultRepo.save(electionResult);
    }

    public List<ElectionResult> getAllElectionResult(){
        return electionResultRepo.findAll();
    }
}
