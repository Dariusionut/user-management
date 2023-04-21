package com.spring.studentmanagement.services;

import com.spring.studentmanagement.models.AppUser;
import com.spring.studentmanagement.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created at 20.04.2023 by Dan.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<AppUser> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public AppUser getUserById(Long userId) {
        return userRepository.getById(userId);
    }
}


