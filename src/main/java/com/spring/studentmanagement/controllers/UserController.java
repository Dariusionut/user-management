package com.spring.studentmanagement.controllers;

import com.spring.studentmanagement.exceptions.UserNotFoundExceptions;
import com.spring.studentmanagement.models.AppUser;
import com.spring.studentmanagement.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created at 4/19/2023 by Darius
 **/

@Controller
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String getUserView(Model model) {
        final List<AppUser> users = this.userService.findAllUsers();
        model.addAttribute("userList", users);
        return "users";
    }

    @GetMapping(path = "/{userId}")
    public String getUserProfile(@PathVariable(name = "userId", required = false) Long userId, Model model) {
        try {
            AppUser user = this.userService.getUserById(userId);
            // add user information to the model for rendering in the view
            model.addAttribute("user", user);
            return "user";
        } catch (UserNotFoundExceptions e) {
            return "redirect:/error";
        }
    }

    @DeleteMapping
    public String deleteUser(@RequestParam("userId") Long userId) {
        this.userService.deleteUserById(userId);
        return "redirect:/users";
    }
}

