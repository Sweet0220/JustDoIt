package com.dormsrl.justdoit.validation;

import org.springframework.stereotype.Component;

@Component
public interface DtoValidator<T> {
    void validate(T dto, ValidationType validationType);
}
