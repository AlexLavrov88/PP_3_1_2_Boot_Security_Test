package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminControllers {

    private final UserService userService;
    private final UserRepository userRepository;

    public AdminControllers(UserServiceImpl userServiceImpl,
                            UserRepository userRepository) {
        this.userService = userServiceImpl;
        this.userRepository = userRepository;
    }

    @GetMapping()
    public String getUsers(Model model) {
        model.addAttribute("user", userService.findAllUsers());
        return "admin/all";
    }

    @GetMapping("/new")
    public String getUserFormCreation(Model model) {
        model.addAttribute("user", new User());
        return "admin/new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteByIdUsers(id);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String getUserFormEdit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.findByIdUsers(id));
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updateUser(id, user);
        return "redirect:/admin";
    }
}
