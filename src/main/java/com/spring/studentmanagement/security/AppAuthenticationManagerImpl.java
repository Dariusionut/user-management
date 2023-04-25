package com.spring.studentmanagement.security;

import com.spring.studentmanagement.controllers.requests.LoginRequest;
import com.spring.studentmanagement.exceptions.AuthenticationException;
import com.spring.studentmanagement.models.AppUser;
import com.spring.studentmanagement.repositories.UserRepository;
import com.spring.studentmanagement.security.utils.Security;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Created at 4/25/2023 by Darius
 **/
@Security
@RequiredArgsConstructor
@Slf4j
public class AppAuthenticationManagerImpl implements AppAuthManager, HandlerInterceptor {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final HttpServletRequest request;

    @Override
    public AppUser authenticate(LoginRequest loginRequest) throws AuthenticationException {
        log.info("Authentication process started....");

        final AppUser user = this.userRepository.findBYUsernameOrEmail(loginRequest.usernameOrEmail())
                .orElseThrow(() -> new AuthenticationException(USERNAME_NOT_FOUND));

        this.validateAuthentication(loginRequest.password(), user);
        request.setAttribute("userPrincipal", user);

        return user;

    }

    private void validateAuthentication(String rawPassword, AppUser user) throws AuthenticationException {
        boolean passwordMatch = this.encoder.matches(rawPassword, user.getPassword());

        if (!passwordMatch) {
            throw new AuthenticationException(INVALID_CREDENTIALS);
        }

        if (!user.isEnabled()) {
            throw new AuthenticationException(ACCOUNT_DISABLED);
        }
    }
}
