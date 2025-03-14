package com.utin.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String Index(Model model) {
        model.addAttribute("name", "Welcome to the SCM Page Index");
        return "index";
    }
    @RequestMapping("/home")
    public String Home(Model model) {
        model.addAttribute("name", "Welcome to the SCM");
        return "home";
    }
}
