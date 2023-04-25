package com.spring.studentmanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created at 4/19/2023 by Darius
 **/

@Controller
public class HomeController {

    @GetMapping(path = "/home")
    public String getHomeView() {

        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }



    @GetMapping(path = "/terms")
    public String termsOfService() {
        return "terms";
    }
}
