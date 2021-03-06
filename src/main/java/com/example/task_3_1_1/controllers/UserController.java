package com.example.task_3_1_1.controllers;

import com.example.task_3_1_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "admin/login";
    }

    @GetMapping(value = "/user")
    public String index(Principal pri, Model modelMap) {
        modelMap.addAttribute("user", userService.getUserByName(pri.getName()));
        return "admin/show_user";
    }
}