package com.dormsrl.justdoit.service.impl;

import com.dormsrl.justdoit.dto.UserDto;
import com.dormsrl.justdoit.entity.User;
import com.dormsrl.justdoit.exception.ValidationException;
import com.dormsrl.justdoit.repository.UserRepository;
import com.dormsrl.justdoit.service.UserService;
import com.dormsrl.justdoit.validation.DtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DtoValidator<UserDto> dtoValidator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, DtoValidator<UserDto> dtoValidator) {
        this.userRepository = userRepository;
        this.dtoValidator = dtoValidator;
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
    public void saveUser(UserDto userDto) throws ValidationException {
        dtoValidator.validate(userDto);
        User user = new User();
        user.setId(null);
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        userRepository.save(user);
    }

    @Override
    public void updateUser(UserDto userDto, Long id) throws ValidationException {
        dtoValidator.validate(userDto);
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            List<String> v = new ArrayList<>();
            v.add("User with id " + id + " does not exist");
            throw new ValidationException(v);
        }
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());
        user.setName(userDto.getName());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
