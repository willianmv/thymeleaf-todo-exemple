package com.example.thymeleaf_todo.controllers;

import com.example.thymeleaf_todo.models.Task;
import com.example.thymeleaf_todo.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;

    @GetMapping("/")
    public ModelAndView list(){
        return new ModelAndView("task/list",
                Map.of("tasks", taskRepository.findAll()));
    }

    @GetMapping("/create")
    public ModelAndView create(){
        return new ModelAndView("task/form",
                Map.of("task", new Task()));
    }

    @PostMapping("/create")
    public String create(Task task){
        taskRepository.save(task);
        return "redirect:/";
    }
}
