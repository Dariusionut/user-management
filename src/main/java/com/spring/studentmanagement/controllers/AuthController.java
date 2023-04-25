package com.spring.studentmanagement.controllers;

import com.spring.studentmanagement.controllers.requests.LoginRequest;
import com.spring.studentmanagement.dto.SignUpRequestDto;
import com.spring.studentmanagement.exceptions.AuthenticationException;
import com.spring.studentmanagement.models.AppUser;
import com.spring.studentmanagement.security.interfaces.SecurityService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    private final SecurityService securityService;
    private final HttpServletRequest request;

    /**
     * This is a mock authentication mechanism
     * For real authentication process we will use Spring Security
     * Method is not completed
     */
    @PostMapping(path = "/login")
    public String authenticate(RedirectAttributes redirectAttributes) {
        log.info("User with ip address = {} is trying to authenticate", this.request.getRemoteAddr());
        final LoginRequest loginRequest = LoginRequest.getLoginRequest(request);
        try {
            final AppUser user = this.securityService.signIn(loginRequest);
            redirectAttributes.addFlashAttribute("userPrincipal", user);
            return "redirect:/users";
        } catch (AuthenticationException e) {
            log.error("Cannot authenticate!");
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/errors/error-401";
        } catch (Exception e) {
            log.error("Internal server error occurred", e);
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/errors/error-500";
        }

    }

    @GetMapping(path = "/register")
    public String registerUser(Model model) {
        model.addAttribute("newUser", new SignUpRequestDto());
        return "register";
    }

    @PostMapping(path = "/register")
    public String registerUser(@ModelAttribute(name = "user") SignUpRequestDto userRequest) {
        try {
            final AppUser savedUser = this.securityService.signUp(userRequest);
            log.info("New user registered: {}", savedUser.getUsername());
            return "redirect:/users";
        } catch (Exception ex) {
            log.info("Failed to register user: {}", userRequest.getUsername(), ex);
            return "redirect:/errors/error-500";
        }
    }


}
