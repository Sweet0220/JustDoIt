package com.dormsrl.justdoit.error;

import lombok.Value;

import java.util.List;

@Value
public class ViolationResponse {
    int status;
    String error;
    String exception;
    List<String> violations;
    String path;
}
