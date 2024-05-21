package com.dormsrl.justdoit.controller;

import com.dormsrl.justdoit.dto.TaskDto;
import com.dormsrl.justdoit.error.ErrorHandler;
import com.dormsrl.justdoit.error.ErrorResponse;
import com.dormsrl.justdoit.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("api/tasks")
@Slf4j
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTaskById(@PathVariable Long id) {
        try {
            TaskDto taskDto = taskService.findById(id);
            if (taskDto == null) {
                return new ResponseEntity<>(null, NOT_FOUND);
            }
            return new ResponseEntity<>(taskDto, OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ErrorResponse errorResponse = ErrorHandler.generateErrorResponse(e, INTERNAL_SERVER_ERROR, "GET api/tasks/" + id);
            return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/by-list-id/{listId}")
    public ResponseEntity<Object> getTasksByListId(@PathVariable Long listId) {
        try {
            List<TaskDto> taskDtoList = taskService.findByListId(listId);
            if (taskDtoList == null || taskDtoList.isEmpty()) {
                return new ResponseEntity<>(null, NOT_FOUND);
            }
            return new ResponseEntity<>(taskDtoList, OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ErrorResponse errorResponse = ErrorHandler.generateErrorResponse(e, INTERNAL_SERVER_ERROR, "GET /api/tasks/by-list-id/" + listId);
            return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTaskById(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
            return new ResponseEntity<>(null, OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ErrorResponse errorResponse = ErrorHandler.generateErrorResponse(e, INTERNAL_SERVER_ERROR, "DELETE /api/tasks/" + id);
            return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createTask(@RequestBody TaskDto taskDto) {
        try {
            taskService.saveTask(taskDto);
            return new ResponseEntity<>(null, CREATED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ErrorResponse errorResponse = ErrorHandler.generateErrorResponse(e, INTERNAL_SERVER_ERROR, "POST /api/tasks");
            return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateTask(@RequestBody TaskDto taskDto) {
        try {
            taskService.updateTask(taskDto);
            return new ResponseEntity<>(null, OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ErrorResponse errorResponse = ErrorHandler.generateErrorResponse(e, INTERNAL_SERVER_ERROR, "PUT /api/tasks");
            return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
        }
    }
}
