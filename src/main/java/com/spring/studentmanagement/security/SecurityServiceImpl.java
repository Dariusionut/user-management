package com.spring.studentmanagement.security;

import com.spring.studentmanagement.controllers.requests.LoginRequest;
import com.spring.studentmanagement.dto.SignUpRequestDto;
import com.spring.studentmanagement.exceptions.AuthenticationException;
import com.spring.studentmanagement.models.AppUser;
import com.spring.studentmanagement.models.Role;
import com.spring.studentmanagement.repositories.RoleRepository;
import com.spring.studentmanagement.security.interfaces.AppAuthManager;
import com.spring.studentmanagement.security.interfaces.SecurityService;
import com.spring.studentmanagement.security.utils.Security;
import com.spring.studentmanagement.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.spring.studentmanagement.security.utils.SecurityConstants.USER_PRINCIPAL;

/**
 * Created at 4/25/2023 by Darius
 **/
@Security
@RequiredArgsConstructor
@Slf4j
public class SecurityServiceImpl implements SecurityService {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final AppAuthManager authenticationManager;
    private final PasswordEncoder encoder;

    @Override
    public AppUser signIn(LoginRequest loginRequest) throws AuthenticationException {
        return this.authenticationManager.authenticate(loginRequest);
    }

    @Override
    public AppUser signUp(SignUpRequestDto signUpRequest) {
        if (!signUpRequest.passwordMatches()) {
            log.error("Passwords does not match!");
            // logic here
        }
        log.info("Started signUp process with request = {}", signUpRequest);
        final String hashedPassword = this.encoder.encode(signUpRequest.getPassword());
        Role role = roleRepository.findById(signUpRequest.getRoleId()).orElse(Role.builder().roleId(1L).roleName("USER").build());
        final AppUser user = signUpRequest.toUser();
        user.setRole(role);
        user.setPassword(hashedPassword);
        log.info("Trying to saveUser: {}", user);
        return userService.saveUser(user);
    }

    @Override
    public void logout(HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(USER_PRINCIPAL) != null) {
            final AppUser userPrincipal = (AppUser) session.getAttribute(USER_PRINCIPAL);
            session.removeAttribute(USER_PRINCIPAL);
            session.invalidate();
            log.info("{} successfully loggedOut!", userPrincipal.getUsername());
        } else {
            log.warn("There is no authenticated user to log out!");
        }
    }
}
