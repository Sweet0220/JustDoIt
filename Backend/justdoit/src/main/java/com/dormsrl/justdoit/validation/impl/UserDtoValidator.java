package com.dormsrl.justdoit.validation.impl;

import com.dormsrl.justdoit.dto.UserDto;
import com.dormsrl.justdoit.repository.UserRepository;
import com.dormsrl.justdoit.validation.DtoValidator;
import com.dormsrl.justdoit.validation.ValidationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDtoValidator implements DtoValidator<UserDto> {

    private final UserRepository userRepository;

    @Autowired
    public UserDtoValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(UserDto dto, ValidationType validationType) {
        List<String> violations = switch (validationType) {
            case CREATE -> validateFieldsWithoutId(dto);
            case UPDATE -> validateFields(dto);
        };
        if (!violations.isEmpty()) {
            //throw new ValidationException(violations);
        }
    }

    // TO-DO: ViolationsClass with its own custom error response
    private List<String> validateFieldsWithoutId(UserDto dto) {
        return null;
    }

    private List<String> validateFields(UserDto dto) {
        return null;
    }
}
