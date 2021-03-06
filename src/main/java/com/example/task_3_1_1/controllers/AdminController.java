package com.example.task_3_1_1.controllers;

import com.example.task_3_1_1.model.Role;
import com.example.task_3_1_1.model.User;
import com.example.task_3_1_1.service.RoleService;
import com.example.task_3_1_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model modelMap) {
        modelMap.addAttribute("users", userService.getAllUsers());
        return "admin/show_admin";
    }

    @GetMapping("/{id}/edit")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/edit";
    }

    @PostMapping("/{id}")
    public String upDate(@ModelAttribute("user") User user,
                         @PathVariable("id") Long id,
                         @RequestParam("role") String[] role) {
        Set<Role> roleSet = new HashSet<>();
        for (String roles : role) {
            roleSet.add(roleService.getRoleByName(roles));
        }
        user.setRoles(roleSet);
        userService.updateUser(user, id);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "admin/new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam("role") String[] role) {
        Set<Role> roleSet = new HashSet<>();
        for (String roles : role) {
            roleSet.add(roleService.getRoleByName(roles));
        }
        user.setRoles(roleSet);
        userService.addUser(user);
        return "redirect:/admin";
    }
}
