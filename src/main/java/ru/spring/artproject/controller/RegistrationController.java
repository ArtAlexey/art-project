package ru.spring.artproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.artproject.domain.Role;
import ru.spring.artproject.domain.User;
import ru.spring.artproject.service.UserService;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    UserService service;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping(path = "/registration")
    public String registration(Model model) {
        return "registration";
    }

    @PostMapping(path = "/registration/save")
    public String saveUser(@RequestParam String name, @RequestParam String username,
                           @RequestParam String password, @RequestParam String confirmedPassword) {
        if (!password.equals(confirmedPassword)) {
            return "redirect:/registration?confirmedError=true";
        }
        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(true);
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        boolean mistake = service.saveUser(user);
        if (!mistake) {
            return "redirect:/registration?confirmedSave=true";
        }
        return "redirect:/login";
    }

}
