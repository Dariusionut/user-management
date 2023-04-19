package com.spring.studentmanagement.controllers;

import com.spring.studentmanagement.models.AppUser;
import com.spring.studentmanagement.models.Role;
import com.spring.studentmanagement.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Mock
    private Model model;

    @Test
    @DisplayName("Should return the user view with a list of all users")
    void getUserViewWithAllUsers() {
        List<AppUser> users =
                List.of(
                        AppUser.builder()
                                .userId(1L)
                                .role(new Role(1L, "ROLE_ADMIN"))
                                .name("John Doe")
                                .username("johndoe")
                                .password("password")
                                .dateAdded(LocalDateTime.now())
                                .build(),
                        AppUser.builder()
                                .userId(2L)
                                .role(new Role(2L, "ROLE_USER"))
                                .name("Jane Doe")
                                .username("janedoe")
                                .password("password")
                                .dateAdded(LocalDateTime.now())
                                .build());

        when(userService.findAllUsers()).thenReturn(users);

        String viewName = userController.getUserView(model);

        assertEquals("users", viewName);
        verify(userService, times(1)).findAllUsers();
        verify(model, times(1)).addAttribute("userList", users);
    }
}