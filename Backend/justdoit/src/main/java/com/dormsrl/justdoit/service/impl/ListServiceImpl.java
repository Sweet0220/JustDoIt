package com.dormsrl.justdoit.service.impl;

import com.dormsrl.justdoit.dto.ListDto;
import com.dormsrl.justdoit.entity.User;
import com.dormsrl.justdoit.entity.enums.ListCategory;
import com.dormsrl.justdoit.exception.ValidationException;
import com.dormsrl.justdoit.repository.ListRepository;
import com.dormsrl.justdoit.repository.UserRepository;
import com.dormsrl.justdoit.service.ListService;
import com.dormsrl.justdoit.validation.DtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListServiceImpl implements ListService {

    private final ListRepository listRepository;
    private final UserRepository userRepository;
    private final DtoValidator<ListDto> dtoValidator;

    @Autowired
    public ListServiceImpl(ListRepository listRepository, UserRepository userRepository, DtoValidator<ListDto> dtoValidator) {
        this.listRepository = listRepository;
        this.userRepository = userRepository;
        this.dtoValidator = dtoValidator;
    }

    @Override
    public ListDto getListById(Long id) {
        com.dormsrl.justdoit.entity.List list = listRepository.findById(id).orElse(null);
        if (list == null) {
            return null;
        }
        return new ListDto(list.getId(), list.getName(), list.getCategory(), list.getPriority());
    }

    @Override
    public List<ListDto> getListsByUserId(Long userId) {
        List<com.dormsrl.justdoit.entity.List> lists = listRepository.findByUserIdOrderByPriorityAsc(userId).orElse(null);
        return getListDtos(lists);
    }

    @Override
    public List<ListDto> getByCategoryAndUserId(ListCategory category, Long userId) {
        List<com.dormsrl.justdoit.entity.List> lists = listRepository.findByUserIdAndCategoryOrderByPriorityAsc(userId, category).orElse(null);
        return getListDtos(lists);
    }

    @Override
    public void saveList(ListDto listDto, Long userId) throws ValidationException {
        dtoValidator.validate(listDto);
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            List<String> v = new ArrayList<>();
            v.add("Cannot add list for inexistent user");
            throw new ValidationException(v);
        }
        com.dormsrl.justdoit.entity.List list = new com.dormsrl.justdoit.entity.List();
        list.setId(null);
        list.setName(listDto.getName());
        list.setCategory(listDto.getCategory());
        list.setPriority(listDto.getPriority());
        list.setUser(user);
        listRepository.save(list);
    }

    @Override
    public void updateList(ListDto listDto, Long id) throws ValidationException {
        dtoValidator.validate(listDto);
        com.dormsrl.justdoit.entity.List list = listRepository.findById(id).orElse(null);
        if (list == null) {
            List<String> v = new ArrayList<>();
            v.add("List with id " + id + " does not exist");
            throw new ValidationException(v);
        }
        list.setName(listDto.getName());
        list.setCategory(listDto.getCategory());
        list.setPriority(listDto.getPriority());
        listRepository.save(list);
    }

    @Override
    public void deleteList(Long id) {
        listRepository.deleteById(id);
    }

    private List<ListDto> getListDtos(List<com.dormsrl.justdoit.entity.List> lists) {
        if (lists == null) {
            return null;
        }
        List<ListDto> listDtos = new ArrayList<>();
        for (com.dormsrl.justdoit.entity.List list : lists) {
            listDtos.add(new ListDto(list.getId(), list.getName(), list.getCategory(), list.getPriority()));
        }
        return listDtos;
    }
}
