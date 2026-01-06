package com.hokte.online_voting_spring_boot.repo;

import com.hokte.online_voting_spring_boot.models.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoterRepo extends JpaRepository<Voter,Long> {
    boolean existsByEmail(String email);
}
