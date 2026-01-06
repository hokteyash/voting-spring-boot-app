package com.hokte.online_voting_spring_boot.repo;

import com.hokte.online_voting_spring_boot.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepo extends JpaRepository<Vote,Long> {

}
