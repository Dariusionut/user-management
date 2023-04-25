package com.spring.studentmanagement.controllers;

import com.spring.studentmanagement.controllers.requests.LoginRequest;
import com.spring.studentmanagement.exceptions.AuthenticationException;
import com.spring.studentmanagement.models.AppUser;
import com.spring.studentmanagement.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created at 4/25/2023 by Darius
 **/

@Controller
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthenticationService authenticationService;
    private final HttpServletRequest request;

    /**
     * This is a mock authentication mechanism
     * For real authentication process we will use Spring Security
     */
    @PostMapping(path = "/login")
    public String authenticate() {
        final LoginRequest loginRequest = LoginRequest.getLoginRequest(request);
        try {
            final AppUser user = this.authenticationService.authenticate(loginRequest);
            // todo to be completed
            return "redirect:/users";
        } catch (AuthenticationException e) {
            log.error("Cannot authenticate!");

            return "errors/error-401"; // todo
        }

    }
}
