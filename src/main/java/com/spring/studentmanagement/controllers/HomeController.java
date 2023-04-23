package com.spring.studentmanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created at 4/19/2023 by Darius
 **/

@Controller
@RequestMapping(path = "/home")
public class HomeController {

    @GetMapping
    public String getHomeView() {

        return "index";
    }

    @GetMapping(path = "/register")
    public String registerUser() {
        return "register";
    }
}
