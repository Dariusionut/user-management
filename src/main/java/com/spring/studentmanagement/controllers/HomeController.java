package com.spring.studentmanagement.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created at 4/19/2023 by Darius
 **/

@Controller
@RequestMapping(path = "/home")
public class HomeController {

    @Value("${db.name}")
    private String databaseName;

    @Value("${db.user}")
    private String databaseUser;

    @Value("${server.port}")
    private int serverPort;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @GetMapping
    public String getHomeView(Model model) {

        model.addAttribute("dbName", this.databaseName);
        model.addAttribute("dbUsername", this.databaseUser);
        model.addAttribute("serverPort", this.serverPort);
        model.addAttribute("contextPath", this.contextPath);
        return "home";
    }
}
