package com.utin.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.utin.scm.entities.User;
import com.utin.scm.forms.UserForm;
import com.utin.scm.services.UserService;


@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
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
    public String register(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }

    @RequestMapping(value = "/do-register", method=RequestMethod.POST)
    public String processRegister(@ModelAttribute UserForm userForm) {
        
        User user = User.builder()
            .name(userForm.getName())
            .email(userForm.getEmail())
            .password(userForm.getPassword())
            .about(userForm.getAbout())
            .phoneNumber(userForm.getPhoneNumber())
            .build();

        System.out.println(userForm);
        User savedUser = userService.saveUser(user);
        System.out.println(savedUser);

        return "redirect:/login";
    }
    
}
