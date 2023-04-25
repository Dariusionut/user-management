package com.spring.studentmanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created at 4/25/2023 by Darius
 **/

@Controller
@RequestMapping(path = "/errors")
public class ErrorController {


    @GetMapping(path = "/error-401")
    public String getUnauthorizedView(Model model) {
        model.addAttribute("errorTitle", "Unauthorized");
        return "errors/error-401";
    }
}
