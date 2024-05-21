package com.dormsrl.justdoit.service;

import com.dormsrl.justdoit.dto.UserDto;

public interface UserService {
    UserDto getUserById(Long id);
    UserDto getUserByUsername(String username);
    UserDto getUserByEmail(String email);
    void saveUser(UserDto userDto);
    void updateUser(UserDto userDto);
    void deleteUser(Long id);
}
