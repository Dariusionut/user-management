package com.spring.studentmanagement.security.interfaces;

import com.spring.studentmanagement.exceptions.AuthenticationException;
import com.spring.studentmanagement.models.AppUser;
import com.spring.studentmanagement.security.utils.Security;

/**
 * Created at 4/25/2023 by Darius
 **/
@Security
public interface AppUserDetailsService {
    String USERNAME_NOT_FOUND = "Username not found";
    AppUser loadByUsernameOrEmail(String usernameOrEmail) throws AuthenticationException;
}
