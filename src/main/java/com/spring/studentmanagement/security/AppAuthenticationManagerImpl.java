package com.spring.studentmanagement.security;

import com.spring.studentmanagement.controllers.requests.LoginRequest;
import com.spring.studentmanagement.exceptions.AuthenticationException;
import com.spring.studentmanagement.models.AppUser;
import com.spring.studentmanagement.repositories.UserRepository;
import com.spring.studentmanagement.security.utils.Security;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created at 4/25/2023 by Darius
 **/
@Security
@RequiredArgsConstructor
@Slf4j
public class AppAuthenticationManagerImpl implements AppAuthManager {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public AppUser authenticate(LoginRequest request) throws AuthenticationException {
        log.info("Authentication process started....");

        final AppUser user = this.userRepository.findBYUsernameOrEmail(request.usernameOrEmail())
                .orElseThrow(() -> new AuthenticationException(USERNAME_NOT_FOUND));

        this.validateAuthentication(request.password(), user);

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
