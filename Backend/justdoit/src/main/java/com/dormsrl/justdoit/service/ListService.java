package com.dormsrl.justdoit.service;


import com.dormsrl.justdoit.dto.ListDto;
import com.dormsrl.justdoit.entity.enums.ListCategory;

import java.util.List;

public interface ListService {
    ListDto getListById(Long id);
    List<ListDto> getListsByUserId(Long userId);
    List<ListDto> getByCategoryAndUserId(ListCategory category, Long userId);
    void saveList(ListDto listDto);
    void updateList(ListDto listDto);
    void deleteList(Long id);

}
