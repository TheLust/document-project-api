package com.iongroup.documentprojectapi.exception;

import com.iongroup.documentprojectapi.util.ExceptionMessage;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public static NotFoundException of(String field) {
        return new NotFoundException(field + ExceptionMessage.NOT_FOUND);
    }
}
