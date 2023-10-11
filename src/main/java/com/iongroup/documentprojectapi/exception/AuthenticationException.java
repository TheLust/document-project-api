package com.iongroup.documentprojectapi.exception;

public class AuthenticationException extends RuntimeException{

    public static final String BAD_CREDENTIALS = "Bad credentials";

    public AuthenticationException(String message) {
        super(message);
    }
}
