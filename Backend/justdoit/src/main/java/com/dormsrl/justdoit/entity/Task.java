package com.dormsrl.justdoit.entity;

import com.dormsrl.justdoit.entity.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @Temporal(TemporalType.DATE)
    private Date deadline;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @ManyToOne(targetEntity = com.dormsrl.justdoit.entity.List.class)
    @JoinColumn(name = "id_list")
    private List list;

    public Task() {
    }
}
