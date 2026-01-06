package com.hokte.online_voting_spring_boot.repo;

import com.hokte.online_voting_spring_boot.models.ElectionResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ElectionResultRepo extends JpaRepository<ElectionResult,Long> {
    Optional<ElectionResult> findByElectionName(String electionName);
}
