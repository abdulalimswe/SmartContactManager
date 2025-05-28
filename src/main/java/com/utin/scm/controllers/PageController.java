package com.utin.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.utin.scm.entities.User;
import com.utin.scm.forms.UserForm;
import com.utin.scm.helpers.Message;
import com.utin.scm.helpers.MessageType;
import com.utin.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


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
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session) {
        if(rBindingResult.hasErrors()){
            return "register";
        }
        // User user = User.builder()
        //     .name(userForm.getName())
        //     .email(userForm.getEmail())
        //     .password(userForm.getPassword())
        //     .about(userForm.getAbout())
        //     .phoneNumber(userForm.getPhoneNumber())
        //     .profilePic("https://img.freepik.com/premium-vector/avatar-profile-icon-flat-style-male-user-profile-vector-illustration-isolated-background-man-profile-sign-business-concept_157943-38764.jpg")
        //     .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://img.freepik.com/premium-vector/avatar-profile-icon-flat-style-male-user-profile-vector-illustration-isolated-background-man-profile-sign-business-concept_157943-38764.jpg");

        
        userService.saveUser(user);

        //register success alert message
        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
        session.setAttribute("message", message);


        return "redirect:/register";
    }
    
}
