package com.spring.studentmanagement.security.services;

import com.spring.studentmanagement.exceptions.AuthenticationException;
import com.spring.studentmanagement.models.AppUser;
import com.spring.studentmanagement.repositories.UserRepository;
import com.spring.studentmanagement.security.interfaces.AppUserDetailsService;
import com.spring.studentmanagement.security.utils.Security;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created at 4/25/2023 by Darius
 **/

@Security
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements AppUserDetailsService {

    private final UserRepository userRepository;

    @Override
    public AppUser loadByUsernameOrEmail(String usernameOrEmail) throws AuthenticationException {
        return this.userRepository.findByUsernameOrEmail(usernameOrEmail)
                .orElseThrow(() -> new AuthenticationException(USERNAME_NOT_FOUND));
    }

}
