package com.dormsrl.justdoit.validation;

import com.dormsrl.justdoit.exception.ValidationException;

public interface DtoValidator<T> {
    void validate(T dto) throws ValidationException;
}
