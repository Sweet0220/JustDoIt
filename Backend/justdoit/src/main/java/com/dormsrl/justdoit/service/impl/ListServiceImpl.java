package com.dormsrl.justdoit.service.impl;

import com.dormsrl.justdoit.dto.ListDto;
import com.dormsrl.justdoit.entity.enums.ListCategory;
import com.dormsrl.justdoit.repository.ListRepository;
import com.dormsrl.justdoit.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListServiceImpl implements ListService {

    private final ListRepository listRepository;

    @Autowired
    public ListServiceImpl(ListRepository listRepository) {
        this.listRepository = listRepository;
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
    public void saveList(ListDto listDto) {

    }

    @Override
    public void updateList(ListDto listDto) {

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
