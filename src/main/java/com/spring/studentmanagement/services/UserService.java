package com.spring.studentmanagement.services;

import com.spring.studentmanagement.models.AppUser;

import java.util.List;

/**
 * Created at 20.04.2023 by Dan.
 */
public interface UserService {
    List<AppUser> findAllUsers();

    void deleteUserById(Long userId);

    AppUser getUserById(Long userId);
}
