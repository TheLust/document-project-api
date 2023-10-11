package com.iongroup.documentprojectapi.exception;

public class NotAuthenticatedException extends RuntimeException{
    public NotAuthenticatedException(String message) {
        super(message);
    }
}
