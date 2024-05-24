package com.dormsrl.justdoit.validation.impl;

import com.dormsrl.justdoit.dto.ListDto;
import com.dormsrl.justdoit.exception.ValidationException;
import com.dormsrl.justdoit.validation.DtoValidator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListDtoValidator implements DtoValidator<ListDto> {

    @Override
    public void validate(ListDto dto) throws ValidationException {
        List<String> violations = new ArrayList<>();

        if (dto.getName() == null) {
            violations.add("Name is required");
        }

        if (dto.getCategory() == null) {
            violations.add("Category is required");
        }

        if (dto.getPriority() == null) {
            violations.add("Priority is required");
        }
        else if (dto.getPriority() < 1 || dto.getPriority() > 5) {
            violations.add("Priority must be between 1 and 5");
        }

        if (!violations.isEmpty()) {
            throw new ValidationException(violations);
        }
    }
}
