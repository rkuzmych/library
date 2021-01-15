package com.rkuzmych.library.service;

import com.rkuzmych.library.controller.UtilsController;
import com.rkuzmych.library.domain.User;
import com.rkuzmych.library.domain.UserRole;
import com.rkuzmych.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service("userService")
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean saveUser(
            User user
    ) {
        if (userExists(user)) {
            return false;
        }

        user.setRoles(Collections.singleton(UserRole.USER));
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return true;
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(UserRole.values())
                .map(UserRole::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(UserRole.valueOf(key));
            }
        }

        userRepository.save(user);
    }

    public boolean checkEmptyFields(User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return true;
        }
        return false;
    }

    public boolean userExists(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if (userFromDb != null) {
           return true;
        }
        return false;
    }


    public boolean validateUser(User user, String passwordConfirm, BindingResult bindingResult, Model model) {
        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);
        boolean differentPasswords = (user.getPassword() != null ) && (!user.getPassword().equals(passwordConfirm));

        if (isConfirmEmpty) {
            model.addAttribute("password2Error", "Password  confirmation not be empty");
        }
        if (differentPasswords) {
            model.addAttribute("passwordError", "Passwords are different!");
        }

        if (bindingResult.hasErrors() || isConfirmEmpty || differentPasswords) {
            Map<String, String> errors = UtilsController.getErrors(bindingResult);

            model.mergeAttributes(errors);

            return false;
        }
        return true;
    }
}
