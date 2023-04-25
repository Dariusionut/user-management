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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
     * Method is not completed
     */
    @PostMapping(path = "/login")
    public String authenticate(RedirectAttributes redirectAttributes) {
        final LoginRequest loginRequest = LoginRequest.getLoginRequest(request);
        try {
            final AppUser user = this.authenticationService.authenticate(loginRequest);
            redirectAttributes.addFlashAttribute("userPrincipal", user);
            return "redirect:/users";
        } catch (AuthenticationException e) {
            log.error("Cannot authenticate!");
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/errors/error-401";
        }

    }
}
