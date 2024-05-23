package com.dormsrl.justdoit.service.impl;

import com.dormsrl.justdoit.dto.TaskDto;
import com.dormsrl.justdoit.entity.Task;
import com.dormsrl.justdoit.exception.ValidationException;
import com.dormsrl.justdoit.repository.ListRepository;
import com.dormsrl.justdoit.repository.TaskRepository;
import com.dormsrl.justdoit.service.TaskService;
import com.dormsrl.justdoit.validation.DtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ListRepository listRepository;
    private final DtoValidator<TaskDto> dtoValidator;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, ListRepository listRepository, DtoValidator<TaskDto> dtoValidator) {
        this.taskRepository = taskRepository;
        this.listRepository = listRepository;
        this.dtoValidator = dtoValidator;
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
    public void saveTask(TaskDto taskDto, Long listId) throws ValidationException {
        dtoValidator.validate(taskDto);
        com.dormsrl.justdoit.entity.List list = listRepository.findById(listId).orElse(null);
        if (list == null) {
            List<String> v = new ArrayList<>();
            v.add("Cannot add task for inexistent list");
            throw new ValidationException(v);
        }
        Task task = new Task();
        task.setId(null);
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        task.setDeadline(taskDto.getDeadline());
        task.setStartDate(taskDto.getStartDate());
        task.setList(list);
        taskRepository.save(task);
    }

    @Override
    public void updateTask(TaskDto taskDto, Long id) throws ValidationException {
        dtoValidator.validate(taskDto);
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            List<String> v = new ArrayList<>();
            v.add("Task with id " + id + " does not exist");
            throw new ValidationException(v);
        }
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        task.setDeadline(taskDto.getDeadline());
        task.setStartDate(taskDto.getStartDate());
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
