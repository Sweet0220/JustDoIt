package com.dormsrl.justdoit.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends Exception {
    private final List<String> violations;

    public ValidationException(List<String> violations) {
        this.violations = violations;
    }



}
