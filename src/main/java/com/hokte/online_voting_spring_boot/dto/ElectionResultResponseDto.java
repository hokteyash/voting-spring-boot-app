package com.hokte.online_voting_spring_boot.dto;

import lombok.Data;

@Data
public class ElectionResultResponseDto {
    private String electionName;
    private Long winnerId;
    private int totalVotes;
    private int winnerVotes;
}
