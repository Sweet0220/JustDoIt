package com.dormsrl.justdoit.repository;

import com.dormsrl.justdoit.entity.List;
import com.dormsrl.justdoit.entity.enums.ListCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ListRepository extends JpaRepository<List, Long> {
    Optional<java.util.List<List>> findByUserIdOrderByPriorityAsc(Long userId);
    Optional<java.util.List<List>> findByUserIdAndCategoryOrderByPriorityAsc(Long userId, ListCategory category);
}
