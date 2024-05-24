package com.dormsrl.justdoit.auth;

import com.dormsrl.justdoit.dto.UserDto;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private String token;
    private UserDto user;
}
