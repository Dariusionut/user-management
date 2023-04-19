package com.spring.studentmanagement.controllers;

import com.spring.studentmanagement.models.AppUser;
import com.spring.studentmanagement.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}

