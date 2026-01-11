package com.hokte.online_voting_spring_boot.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CandidateUpdateDto {

    @Size(min = 1, message = "Name cannot be empty")
    private String name;

    @Size(min = 1, message = "Party name cannot be empty")
    private String party;
}
