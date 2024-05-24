package com.dormsrl.justdoit.dto;

import com.dormsrl.justdoit.entity.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "dd/MM/yyyy")
    Date deadline;
    @JsonFormat(pattern = "dd/MM/yyyy")
    Date startDate;
}