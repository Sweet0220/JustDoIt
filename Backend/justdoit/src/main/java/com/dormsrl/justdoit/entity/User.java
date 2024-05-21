package com.dormsrl.justdoit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String password;
    private String username;
    private String name;
    @OneToMany(targetEntity = List.class, mappedBy = "user")
    private java.util.List<com.dormsrl.justdoit.entity.List> lists;

    public User() {
    }
}
