package com.dormsrl.justdoit.repository;

import com.dormsrl.justdoit.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<List<Task>> findByListId(Long listId);
}
