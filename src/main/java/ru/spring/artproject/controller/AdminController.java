package ru.spring.artproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spring.artproject.service.UserService;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/admin")
    String allUsers(Model model) {
        model.addAttribute("allUsers",userService.allUsers());
        return "admin";
    }

    @PostMapping(path = "/admin/delete")
    String deleteUser(@RequestParam String userId) {
        Long id = Long.parseLong(userId, 10);
        System.out.println(id);
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
