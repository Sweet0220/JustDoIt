package com.dormsrl.justdoit.error;

import org.springframework.http.HttpStatus;

public class ErrorHandler {
    public static ErrorResponse generateErrorResponse(Exception e, HttpStatus status, String path) {
        return new ErrorResponse(status.value(), status.name(), e.getClass().getCanonicalName(), e.getMessage(), path);
    }
}
