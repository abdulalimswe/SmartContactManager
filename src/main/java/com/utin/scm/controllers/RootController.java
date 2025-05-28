package com.utin.scm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.utin.scm.entities.User;
import com.utin.scm.helpers.Helper;
import com.utin.scm.services.UserService;

@ControllerAdvice
public class RootController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;
    
    @ModelAttribute
    public void addLoggedInUserinfo(Model model, Authentication authentication){

        //check auth because user not print if auth is null
        if(authentication == null){
            return;
        }
        // String name = principal.getName();
        String username = Helper.getEmailOfLoggedInUser(authentication);

        logger.info("User Log in: {}", username);

        User user = userService.getUserByEmail(username);
        System.out.println(user);
        System.out.println(user.getName());
        System.out.println(user.getEmail());
        model.addAttribute("loggedInUser",user);
    }
}
