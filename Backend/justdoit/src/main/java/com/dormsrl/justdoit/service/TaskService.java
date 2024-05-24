package com.dormsrl.justdoit.service;


import com.dormsrl.justdoit.dto.TaskDto;
import com.dormsrl.justdoit.exception.ValidationException;

import java.util.List;

public interface TaskService {
    TaskDto findById(Long id);
    List<TaskDto> findByListId(Long listId);
    void saveTask(TaskDto taskDto, Long listId) throws ValidationException;
    void updateTask(TaskDto taskDto, Long id) throws ValidationException;
    void deleteTask(Long id);
}
