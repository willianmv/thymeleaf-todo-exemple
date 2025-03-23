package com.example.thymeleaf_todo.controllers;

import com.example.thymeleaf_todo.models.Task;
import com.example.thymeleaf_todo.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;

    @GetMapping()
    public ModelAndView list(){
        return new ModelAndView("task/list",
                Map.of("tasks", taskRepository.findAll()));
    }
}
