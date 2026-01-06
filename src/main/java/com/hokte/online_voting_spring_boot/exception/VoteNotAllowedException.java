package com.hokte.online_voting_spring_boot.exception;

public class VoteNotAllowedException extends RuntimeException {
    public VoteNotAllowedException(String message) {
        super(message);
    }
}
