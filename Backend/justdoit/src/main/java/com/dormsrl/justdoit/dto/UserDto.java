package com.dormsrl.justdoit.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.dormsrl.justdoit.entity.User}
 */
@Value
public class UserDto implements Serializable {
    Long id;
    String email;
    String password;
    String username;
    String name;
}