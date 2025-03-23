package com.example.thymeleaf_todo.repositories;

import com.example.thymeleaf_todo.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
