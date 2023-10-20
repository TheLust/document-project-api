package com.iongroup.documentprojectapi.advice;

import com.iongroup.documentprojectapi.exception.AlreadyExistsException;
import com.iongroup.documentprojectapi.exception.FileSaveException;
import com.iongroup.documentprojectapi.exception.NotAuthenticatedException;
import com.iongroup.documentprojectapi.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<ExceptionResponse> handleException(RuntimeException e) {
        return new ResponseEntity<>(
                new ExceptionResponse(e.getMessage(), new Date().getTime()),
                HttpStatus.BAD_REQUEST
        );
    }


    @ExceptionHandler(AlreadyExistsException.class)
    private ResponseEntity<ExceptionResponse> handleException(AlreadyExistsException e) {
        return new ResponseEntity<>(
                new ExceptionResponse(e.getMessage(), new Date().getTime()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ExceptionResponse> handleException(NotFoundException e) {
        return new ResponseEntity<>(
                new ExceptionResponse(e.getMessage(), new Date().getTime()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    private ResponseEntity<ExceptionResponse> handleException(MissingServletRequestParameterException e) {
        return new ResponseEntity<>(
                new ExceptionResponse(e.getMessage(), new Date().getTime()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(FileSaveException.class)
    private ResponseEntity<ExceptionResponse> handleException(FileSaveException e) {
        return new ResponseEntity<>(
                new ExceptionResponse(e.getMessage(), new Date().getTime()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    private ResponseEntity<ExceptionResponse> handleException(AccessDeniedException e) {
        return new ResponseEntity<>(
                new ExceptionResponse(e.getMessage(), new Date().getTime()),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(NotAuthenticatedException.class)
    private ResponseEntity<ExceptionResponse> handleException(NotAuthenticatedException e) {
        return new ResponseEntity<>(
                new ExceptionResponse(e.getMessage(), new Date().getTime()),
                HttpStatus.UNAUTHORIZED
        );
    }
}
