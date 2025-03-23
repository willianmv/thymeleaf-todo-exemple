package com.example.thymeleaf_todo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    @GetMapping()
    public ModelAndView home(){
        var modelAndView = new ModelAndView("home");
        modelAndView.addObject("nome", " Nome Testes");
        var listaTestes = List.of("Teste1", "Teste2", "Teste3", "Teste4");
        modelAndView.addObject("testes", listaTestes);
        modelAndView.addObject("ehprogramador", true);
        return modelAndView;
    }
}
