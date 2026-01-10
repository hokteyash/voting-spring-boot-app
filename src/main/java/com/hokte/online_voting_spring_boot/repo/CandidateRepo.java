package com.hokte.online_voting_spring_boot.repo;

import com.hokte.online_voting_spring_boot.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate, Long> {
    List<Candidate> findAllByOrderByVoteCountDesc();

}
