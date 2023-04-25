package com.spring.studentmanagement.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @GetMapping(
            path = "/error-500",
            produces = MediaType.TEXT_HTML_VALUE,
            headers = {HttpHeaders.ACCEPT, HttpHeaders.ACCEPT_ENCODING, HttpHeaders.ACCEPT_LANGUAGE,
                    HttpHeaders.CACHE_CONTROL, HttpHeaders.CONNECTION, HttpHeaders.HOST, HttpHeaders.REFERER,
                    HttpHeaders.USER_AGENT}
    )
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String getInternalServerErrorView(Model model) {
        model.addAttribute("errorTitle", "Internal Server Error");
        model.addAttribute("errorMessage", "Sorry, an internal server error has occurred. We apologize for the inconvenience. Please try again later, or contact our support team if the problem persists.");
        return "errors/error-status";
    }
}
