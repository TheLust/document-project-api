package com.iongroup.documentprojectapi.exception;

import com.iongroup.documentprojectapi.util.ExceptionMessage;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String message) {
        super(message);
    }

    public static AlreadyExistsException of(String entity, String field) {
        return new AlreadyExistsException(getMessage(entity, field));
    }

    public static String getMessage(String entity, String field) {
        return entity + " with this " + field.toLowerCase() + ExceptionMessage.ALREADY_EXISTS;
    }
}
