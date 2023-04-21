package com.spring.studentmanagement.services;

import com.spring.studentmanagement.models.AppUser;
import com.spring.studentmanagement.models.Role;
import com.spring.studentmanagement.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("Should return all users when findAllUsers is called")
    void findAllUsersReturnsAllUsers() {
        List<AppUser> expectedUsers = new ArrayList<>();
        Role role = new Role(1L, "ROLE_ADMIN");
        expectedUsers.add(
                new AppUser(1L, role, "John Doe", "johndoe", "password123", LocalDateTime.now()));
        expectedUsers.add(
                new AppUser(2L, role, "Jane Doe", "janedoe", "password456", LocalDateTime.now()));

        when(userRepository.findAll()).thenReturn(expectedUsers);

        List<AppUser> actualUsers = userService.findAllUsers();

        assertEquals(
                expectedUsers, actualUsers, "The returned users should match the expected users");
        verify(userRepository, times(1)).findAll();
    }
}