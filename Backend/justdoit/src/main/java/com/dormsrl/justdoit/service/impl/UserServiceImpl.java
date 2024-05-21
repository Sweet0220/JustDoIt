package com.dormsrl.justdoit.service.impl;

import com.dormsrl.justdoit.dto.UserDto;
import com.dormsrl.justdoit.entity.User;
import com.dormsrl.justdoit.repository.UserRepository;
import com.dormsrl.justdoit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return null;
        }
        return new UserDto(user.getId(), user.getEmail(), user.getPassword(), user.getUsername(), user.getName());
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return null;
        }
        return new UserDto(user.getId(), user.getEmail(), user.getPassword(), user.getUsername(), user.getName());
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return null;
        }
        return new UserDto(user.getId(), user.getEmail(), user.getPassword(), user.getUsername(), user.getName());
    }

    @Override
    public void saveUser(UserDto userDto) {

    }

    @Override
    public void updateUser(UserDto userDto) {

    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
