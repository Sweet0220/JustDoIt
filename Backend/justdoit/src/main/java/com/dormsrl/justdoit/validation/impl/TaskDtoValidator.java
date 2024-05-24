package com.dormsrl.justdoit.validation.impl;

import com.dormsrl.justdoit.dto.TaskDto;
import com.dormsrl.justdoit.exception.ValidationException;
import com.dormsrl.justdoit.validation.DtoValidator;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskDtoValidator implements DtoValidator<TaskDto> {
    @Override
    public void validate(TaskDto dto) throws ValidationException {
        List<String> violations = new ArrayList<>();

        if (dto.getTitle() == null) {
            violations.add("Title is required");
        }

        if (!violations.isEmpty()) {
            throw new ValidationException(violations);
        }
    }
}
