package com.hokte.online_voting_spring_boot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class ElectionResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Election name is required")
    private String electionName;

    private int totalVotes;

    @OneToOne
    @JoinColumn(name = "winner_id")
    private Candidate candidate;
}
