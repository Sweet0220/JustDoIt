package com.dormsrl.justdoit.controller;

import com.dormsrl.justdoit.dto.UserDto;
import com.dormsrl.justdoit.error.ErrorHandler;
import com.dormsrl.justdoit.error.ErrorResponse;
import com.dormsrl.justdoit.error.ViolationResponse;
import com.dormsrl.justdoit.exception.ValidationException;
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
        catch (ValidationException v) {
            log.error(v.getMessage(), v);
            ViolationResponse violationResponse = ErrorHandler.generateViolationResponse(v, BAD_REQUEST, "POST /api/users");
            return new ResponseEntity<>(violationResponse, BAD_REQUEST);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            ErrorResponse errorResponse = ErrorHandler.generateErrorResponse(e, INTERNAL_SERVER_ERROR, "POST /api/users");
            return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody UserDto userDto, @PathVariable Long id) {
        try {
            userService.updateUser(userDto, id);
            return new ResponseEntity<>(null, OK);
        }
        catch (ValidationException v) {
            log.error(v.getMessage(), v);
            ViolationResponse violationResponse = ErrorHandler.generateViolationResponse(v, BAD_REQUEST, "PUT /api/users/" + id);
            return new ResponseEntity<>(violationResponse, BAD_REQUEST);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            ErrorResponse errorResponse = ErrorHandler.generateErrorResponse(e, INTERNAL_SERVER_ERROR, "PUT /api/users/" + id);
            return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
        }
    }

}
