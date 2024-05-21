package com.dormsrl.justdoit.service;


import com.dormsrl.justdoit.dto.TaskDto;

import java.util.List;

public interface TaskService {
    TaskDto findById(Long id);
    List<TaskDto> findByListId(Long listId);
    void saveTask(TaskDto taskDto);
    void updateTask(TaskDto taskDto);
    void deleteTask(Long id);
}
