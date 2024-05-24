package com.dormsrl.justdoit.error;

import com.dormsrl.justdoit.exception.ValidationException;
import org.springframework.http.HttpStatus;

public class ErrorHandler {
    public static ErrorResponse generateErrorResponse(Exception e, HttpStatus status, String path) {
        return new ErrorResponse(status.value(), status.name(), e.getClass().getCanonicalName(), e.getMessage(), path);
    }

    public static ViolationResponse generateViolationResponse(ValidationException e, HttpStatus status, String path) {
        return new ViolationResponse(status.value(), status.name(), e.getClass().getCanonicalName(), e.getViolations(), path);
    }
}
