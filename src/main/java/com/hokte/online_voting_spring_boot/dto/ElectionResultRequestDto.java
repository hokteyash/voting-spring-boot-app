package com.hokte.online_voting_spring_boot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ElectionResultRequestDto {

    @NotBlank(message = "Election name is required")
    private String electionName;
}
