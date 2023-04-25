package com.spring.studentmanagement.services;

import com.spring.studentmanagement.controllers.requests.LoginRequest;
import com.spring.studentmanagement.models.AppUser;

/**
 * Created at 4/25/2023 by Darius
 **/
public interface AppAuthManager {
    AppUser authenticate(LoginRequest loginRequest);
}
