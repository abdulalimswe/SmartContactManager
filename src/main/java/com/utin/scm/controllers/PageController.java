package com.utin.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping("/about")
    public String About(Model model) {
        model.addAttribute("name", "About Page");
        return "about";
    }

    @RequestMapping("/services")
    public String servicesPage(Model model) {
        model.addAttribute("name", "Services Page");
        return "services";
    }

    @GetMapping("/contact")
    public String contact() {
        return new String("contact");
    }

    @GetMapping("/login")
    public String login() {
        return new String("login");
    }

    @GetMapping("/register")
    public String register() {
        return new String("register");
    }
}
