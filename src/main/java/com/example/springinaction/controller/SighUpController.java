package com.example.springinaction.controller;

import com.example.springinaction.service.UserService;
import com.example.springinaction.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/signup")
public class SighUpController {

    UserService userService;

    @ModelAttribute(name = "user")
    public User addUserToModel() {
        return new User();
    }

    @GetMapping
    public String showSighUpForm() {
        return "signUp";
    }

    @PostMapping
    public String saveUser(@Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            log.info("Validation error: {}", user);
            System.out.println(user);
            return "signUp";
        }
        userService.save(user);


        log.info("User saved: {}", user);
        return "redirect:/design";
    }
}
