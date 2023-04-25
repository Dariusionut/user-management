package com.spring.studentmanagement.controllers;

import com.spring.studentmanagement.exceptions.UserNotFoundException;
import com.spring.studentmanagement.models.AppUser;
import com.spring.studentmanagement.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@Slf4j
public class UserController {

    private final UserService userService;
    private final HttpServletRequest request;

    @GetMapping
    public String getUserView(Model model) {
        final List<AppUser> users = this.userService.findAllUsers();
        model.addAttribute("userList", users);
        return "users";
    }

    @GetMapping(path = "/{userId}")
    public String getUserProfile(@PathVariable(name = "userId", required = false) Long userId, Model model) {
        log.info("principal =   {} ", request.getSession().getAttribute("userPrincipal"));
        try {
            AppUser user = this.userService.getUserById(userId);
            // add user information to the model for rendering in the view
            model.addAttribute("user", user);
            return "user";
        } catch (UserNotFoundException e) {
            return "redirect:/error";
        }
    }

    @DeleteMapping
    public String deleteUser(@RequestParam("userId") Long userId) {
        this.userService.deleteUserById(userId);
        return "redirect:/users";
    }


    @PostMapping(path = "/register")
    public ResponseEntity<?> registerUser(@RequestBody AppUser user) {
        log.info("Starting to save new user" + user);
        try {
            AppUser savedUser =this.userService.saveUser(user);
            log.info("New user registered: {}", savedUser.getUsername());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (DataAccessException ex) {
            log.info("Failed to register user: {}", user.getUsername(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}

