package com.iongroup.documentprojectapi.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ExceptionResponse {
    private String message;
    private long timestamp;
}
