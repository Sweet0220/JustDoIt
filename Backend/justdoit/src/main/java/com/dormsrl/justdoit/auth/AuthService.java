package com.dormsrl.justdoit.auth;

import com.dormsrl.justdoit.dto.UserDto;
import com.dormsrl.justdoit.entity.User;
import com.dormsrl.justdoit.security.jwt.JwtUtil;
import com.dormsrl.justdoit.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(AuthenticationManager authManager, UserDetailsServiceImpl userDetailsService, JwtUtil jwtUtil) {
        this.authManager = authManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) throws Exception {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (AuthenticationException e) {
            throw new Exception("Invalid credentials");
        }
        UserDetails user = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtUtil.generateToken(user);
        authenticationResponse.setToken(token);
        UserDto userDto = new UserDto(((User) user).getId(), ((User) user).getEmail(), user.getPassword(), user.getUsername(), ((User) user).getName());
        authenticationResponse.setUser(userDto);
        return authenticationResponse;
    }

}
