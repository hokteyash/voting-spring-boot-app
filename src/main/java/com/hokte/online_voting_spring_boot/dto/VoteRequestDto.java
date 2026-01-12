package com.hokte.online_voting_spring_boot.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VoteRequestDto {

    @NotNull(message = "Voter id is required")
    private Long voterId;

    @NotNull(message = "Candidate id is required")
    private Long candidateId;
}
