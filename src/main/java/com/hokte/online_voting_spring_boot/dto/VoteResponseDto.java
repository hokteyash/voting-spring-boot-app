package com.hokte.online_voting_spring_boot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteResponseDto {
    private String message;
    private boolean success;
    private Long candidateId;
    private Long voterId;
}
