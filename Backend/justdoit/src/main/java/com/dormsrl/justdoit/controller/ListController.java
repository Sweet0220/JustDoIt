package com.dormsrl.justdoit.controller;

import com.dormsrl.justdoit.dto.ListDto;
import com.dormsrl.justdoit.entity.enums.ListCategory;
import com.dormsrl.justdoit.error.ErrorHandler;
import com.dormsrl.justdoit.error.ErrorResponse;
import com.dormsrl.justdoit.service.ListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/lists")
@Slf4j
public class ListController {

    private final ListService listService;

    @Autowired
    public ListController(ListService listService) {
        this.listService = listService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getListById(@PathVariable("id") Long id) {
        try {
            ListDto listDto = listService.getListById(id);
            if (listDto == null) {
                return new ResponseEntity<>(null, NOT_FOUND);
            }
            return new ResponseEntity<>(listDto, OK);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            ErrorResponse errorResponse = ErrorHandler.generateErrorResponse(e, INTERNAL_SERVER_ERROR, "GET api/lists/" + id);
            return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/by-user-id/{userId}")
    public ResponseEntity<Object> getListByUserId(@PathVariable("userId") Long userId) {
        try {
            List<ListDto> listDtos = listService.getListsByUserId(userId);
            if (listDtos == null) {
                return new ResponseEntity<>(null, NOT_FOUND);
            }
            return new ResponseEntity<>(listDtos, OK);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            ErrorResponse errorResponse = ErrorHandler.generateErrorResponse(e, INTERNAL_SERVER_ERROR, "GET api/lists/by-user-id/" + userId);
            return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getListsByUserIdAndCategory(@RequestParam("userId") Long userId, @RequestParam("category")ListCategory category) {
        try {
            List<ListDto> listDtos = listService.getByCategoryAndUserId(category, userId);
            if (listDtos == null) {
                return new ResponseEntity<>(null, NOT_FOUND);
            }
            return new ResponseEntity<>(listDtos, OK);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            ErrorResponse errorResponse = ErrorHandler.generateErrorResponse(e, INTERNAL_SERVER_ERROR, "GET api/lists?userId=" + userId + "&category=" + category);
            return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteListById(@PathVariable("id") Long id) {
        try {
            listService.deleteList(id);
            return new ResponseEntity<>(null, OK);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            ErrorResponse errorResponse = ErrorHandler.generateErrorResponse(e, INTERNAL_SERVER_ERROR, "DELETE /api/lists/" + id);
            return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createList(@RequestBody ListDto listDto) {
        try {
            listService.saveList(listDto);
            return new ResponseEntity<>(null, CREATED);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            ErrorResponse errorResponse = ErrorHandler.generateErrorResponse(e, INTERNAL_SERVER_ERROR, "POST /api/lists");
            return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateList(@RequestBody ListDto listDto) {
        try {
            listService.updateList(listDto);
            return new ResponseEntity<>(null, OK);
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            ErrorResponse errorResponse = ErrorHandler.generateErrorResponse(e, INTERNAL_SERVER_ERROR, "PUT /api/lists");
            return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
        }
    }
}