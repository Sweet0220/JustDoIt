package com.dormsrl.justdoit.service;

import com.dormsrl.justdoit.dto.UserDto;
import com.dormsrl.justdoit.exception.ValidationException;

public interface UserService {
    UserDto getUserById(Long id);
    UserDto getUserByUsername(String username);
    UserDto getUserByEmail(String email);
    void saveUser(UserDto userDto) throws ValidationException;
    void updateUser(UserDto userDto, Long id) throws ValidationException;
    void deleteUser(Long id);
}
