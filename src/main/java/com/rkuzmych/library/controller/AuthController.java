package com.rkuzmych.library.controller;

import com.rkuzmych.library.service.UserService;
import com.rkuzmych.library.domain.User;
import com.rkuzmych.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public AuthController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ) {
        boolean haveEmptyFields = userService.checkEmptyFields(user, bindingResult, model);
        if (haveEmptyFields) {
            model.addAttribute("message", "You have epmty fields!");
            return "registration";
        }


        if (userService.userExists(user)) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }

        userService.saveUser(user);

        return "redirect:login";
    }
}