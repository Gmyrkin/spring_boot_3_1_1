package com.example.education_3_1_1.controller;

import com.example.education_3_1_1.model.User;
import com.example.education_3_1_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AdminController {
    private final UserService userService;

    @Autowired (required = true)
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping ("/user")
    public String findAll(Model model){
    List<User> users = userService.findAll();
    model.addAttribute("users",users);
        return "user-list";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser( User user){
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUser (@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user",user);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
    }
}
