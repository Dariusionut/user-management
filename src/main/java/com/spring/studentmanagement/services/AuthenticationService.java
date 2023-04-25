package com.spring.studentmanagement.services;

import com.spring.studentmanagement.controllers.requests.LoginRequest;
import com.spring.studentmanagement.exceptions.AuthenticationException;
import com.spring.studentmanagement.models.AppUser;
import com.spring.studentmanagement.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created at 4/25/2023 by Darius
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService implements AppAuthManager {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public AppUser authenticate(LoginRequest request) {
        log.info("Trying to authenticate");

        final AppUser user = this.userRepository.findBYUsernameOrEmail(request.usernameOrEmail())
                .orElseThrow(() -> new AuthenticationException("User not found!"));

        final String rawPassword = request.password();

        boolean passwordMatch = this.encoder.matches(rawPassword, user.getPassword());

        if (!passwordMatch) {
            throw new AuthenticationException("Invalid credentials!");
        }

        if (!user.isEnabled()) {
            throw new AuthenticationException("Account is disabled!");
        }

        return user;

    }
}
