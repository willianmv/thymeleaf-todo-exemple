package com.example.thymeleaf_todo.controllers;

import com.example.thymeleaf_todo.models.Task;
import com.example.thymeleaf_todo.repositories.TaskRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
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
    public String create(@Valid Task task, BindingResult result){
        if(result.hasErrors()) return "task/form";
        taskRepository.save(task);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id){
        return new ModelAndView("task/form",
                Map.of("task", taskRepository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))));
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid Task task, BindingResult result){
        if(result.hasErrors()) return "task/form";
        taskRepository.save(task);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new ModelAndView("task/delete", Map.of("task", task));
    }

    @PostMapping("/delete/{id}")
    public String delete(Task task){
        taskRepository.delete(task);
        return "redirect:/";
    }

    @PostMapping("/finish/{id}")
    public String finish(@PathVariable Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        task.markAsFinished();
        taskRepository.save(task);
        return "redirect:/";
    }

}
