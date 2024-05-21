package com.dormsrl.justdoit.controller;

import com.dormsrl.justdoit.dto.UserDto;
import com.dormsrl.justdoit.error.ErrorHandler;
import com.dormsrl.justdoit.error.ErrorResponse;
import com.dormsrl.justdoit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable(name = "userId") Long userId) {
        try {
            UserDto userDto = userService.getUserById(userId);
            if (userDto != null) {
                return new ResponseEntity<>(userDto, OK);
            }
            return new ResponseEntity<>(null, NOT_FOUND);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            ErrorResponse errorResponse = ErrorHandler.generateErrorResponse(e, INTERNAL_SERVER_ERROR, "GET /api/users/" + userId);
            return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/by-username/{username}")
    public ResponseEntity<Object> getUserByUsername(@PathVariable(name = "username") String username) {
        try {
            UserDto userDto = userService.getUserByUsername(username);
            if (userDto != null) {
                return new ResponseEntity<>(userDto, OK);
            }
            return new ResponseEntity<>(null, NOT_FOUND);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            ErrorResponse errorResponse = ErrorHandler.generateErrorResponse(e, INTERNAL_SERVER_ERROR, "GET /api/users/by-username/" + username);
            return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/by-email/{email}")
    public ResponseEntity<Object> getUserByEmail(@PathVariable(name = "email") String email) {
        try {
            UserDto userDto = userService.getUserByEmail(email);
            if (userDto != null) {
                return new ResponseEntity<>(userDto, OK);
            }
            return new ResponseEntity<>(null, NOT_FOUND);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            ErrorResponse errorResponse = ErrorHandler.generateErrorResponse(e, INTERNAL_SERVER_ERROR, "GET /api/users/by-email/" + email);
            return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Object> deleteUserById(@PathVariable(name = "userId") Long userId) {
        try {
            userService.deleteUser(userId);
            return new ResponseEntity<>(null, OK);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            ErrorResponse errorResponse = ErrorHandler.generateErrorResponse(e, INTERNAL_SERVER_ERROR, "DELETE /api/users/" + userId);
            return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserDto userDto) {
        try {
            userService.saveUser(userDto);
            return new ResponseEntity<>(null, CREATED);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            ErrorResponse errorResponse = ErrorHandler.generateErrorResponse(e, INTERNAL_SERVER_ERROR, "POST /api/users");
            return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody UserDto userDto) {
        try {
            userService.updateUser(userDto);
            return new ResponseEntity<>(null, OK);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            ErrorResponse errorResponse = ErrorHandler.generateErrorResponse(e, INTERNAL_SERVER_ERROR, "PUT /api/users");
            return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
        }
    }

}
