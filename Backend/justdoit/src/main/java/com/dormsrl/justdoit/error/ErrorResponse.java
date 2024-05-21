package com.dormsrl.justdoit.error;

import lombok.Value;

@Value
public class ErrorResponse {
    int status;
    String error;
    String exception;
    String message;
    String path;
}
