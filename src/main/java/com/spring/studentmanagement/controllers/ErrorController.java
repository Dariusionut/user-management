package com.spring.studentmanagement.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.MediaType.*;

/**
 * Created at 4/25/2023 by Darius
 **/

@Controller
@RequestMapping(path = "/errors")
public class ErrorController {


    @GetMapping(
            path = "/error-401",
            produces = TEXT_HTML_VALUE,
            headers = {ACCEPT, ACCEPT_ENCODING, ACCEPT_LANGUAGE, CACHE_CONTROL, CONNECTION, HOST, REFERER, USER_AGENT}
    )
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public String getUnauthorizedView(Model model) {
        model.addAttribute("errorTitle", "Unauthorized");
        return "errors/error-401";
    }

    @GetMapping(
            path = "/error-403",
            produces = TEXT_HTML_VALUE,
            headers = {ACCEPT, ACCEPT_ENCODING, ACCEPT_LANGUAGE, CACHE_CONTROL, CONNECTION, HOST, REFERER, USER_AGENT}
    )
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public String getAuthenticationErrorView(Model model) {
        model.addAttribute("errorTitle", "No access rights to the content");
        return "error-403";
    }
}
