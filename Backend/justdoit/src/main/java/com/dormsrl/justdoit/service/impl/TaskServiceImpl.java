package com.dormsrl.justdoit.service.impl;

import com.dormsrl.justdoit.dto.TaskDto;
import com.dormsrl.justdoit.entity.Task;
import com.dormsrl.justdoit.repository.TaskRepository;
import com.dormsrl.justdoit.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskDto findById(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            return null;
        }
        return new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getStatus(), task.getDeadline(), task.getStartDate());
    }

    @Override
    public List<TaskDto> findByListId(Long listId) {
        List<Task> taskList = taskRepository.findByListId(listId).orElse(null);
        if (taskList == null) {
            return null;
        }
        List<TaskDto> taskDtoList = new ArrayList<>();
        for (Task task : taskList) {
            taskDtoList.add(new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getStatus(), task.getDeadline(), task.getStartDate()));
        }
        return taskDtoList;
    }

    @Override
    public void saveTask(TaskDto taskDto) {

    }

    @Override
    public void updateTask(TaskDto taskDto) {

    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
