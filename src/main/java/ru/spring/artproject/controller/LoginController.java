package ru.spring.artproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {


    @GetMapping(path = "/login")
    String login(Model model) {
        return "login";
    }
}
