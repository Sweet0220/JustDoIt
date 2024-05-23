package com.dormsrl.justdoit.validation.impl;

import com.dormsrl.justdoit.dto.UserDto;
import com.dormsrl.justdoit.exception.ValidationException;
import com.dormsrl.justdoit.repository.UserRepository;
import com.dormsrl.justdoit.validation.DtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class UserDtoValidator implements DtoValidator<UserDto> {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private final UserRepository userRepository;

    @Autowired
    public UserDtoValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(UserDto dto) throws ValidationException {
        List<String> violations = new ArrayList<>();

        if (dto.getEmail() == null) {
            violations.add("Email is required");
        }
        else if (!VALID_EMAIL_ADDRESS_REGEX.matcher(dto.getEmail()).matches()) {
            violations.add("Email address is invalid");
        }
        else if (userRepository.existsByEmail(dto.getEmail())) {
            violations.add("Email address is already in use");
        }

        if (dto.getUsername() == null) {
            violations.add("Username is required");
        }
        else if (userRepository.existsByUsername(dto.getUsername())) {
            violations.add("Username is already in use");
        }

        if (dto.getPassword() == null) {
            violations.add("Password is required");
        }
        else if (!dto.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
            violations.add("Password must contain at least 8 characters, small letter, capital letter, number and special character.");
        }

        if(dto.getName() == null) {
            violations.add("Name is required");
        }

        if (!violations.isEmpty()) {
            throw new ValidationException(violations);
        }
    }
}
