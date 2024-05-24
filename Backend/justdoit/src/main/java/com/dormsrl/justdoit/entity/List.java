package com.dormsrl.justdoit.entity;

import com.dormsrl.justdoit.entity.enums.ListCategory;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class List {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ListCategory category;
    private int priority;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "id_user")
    private User user;
    @OneToMany(targetEntity = Task.class, mappedBy="list")
    private java.util.List<Task> tasks;

    public List() {
    }
}
