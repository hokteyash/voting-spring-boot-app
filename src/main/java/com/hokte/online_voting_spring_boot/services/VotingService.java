package com.hokte.online_voting_spring_boot.services;

import com.hokte.online_voting_spring_boot.dto.VoteRequestDto;
import com.hokte.online_voting_spring_boot.models.Vote;
import com.hokte.online_voting_spring_boot.repo.CandidateRepo;
import com.hokte.online_voting_spring_boot.repo.VoteRepo;
import com.hokte.online_voting_spring_boot.repo.VoterRepo;
import org.springframework.stereotype.Service;

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

    public Vote castVote(VoteRequestDto voteRequestDto) {
        return new Vote();
    }
}
