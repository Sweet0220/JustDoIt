package com.dormsrl.justdoit.dto;

import com.dormsrl.justdoit.entity.enums.TaskStatus;
import lombok.Value;

import java.io.Serializable;
import java.sql.Date;

/**
 * DTO for {@link com.dormsrl.justdoit.entity.Task}
 */
@Value
public class TaskDto implements Serializable {
    Long id;
    String title;
    String description;
    TaskStatus status;
    Date deadline;
    Date startDate;
}