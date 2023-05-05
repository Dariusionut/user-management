package com.spring.studentmanagement.controllers;

import com.spring.studentmanagement.controllers.requests.LoginRequest;
import com.spring.studentmanagement.dto.SignUpRequestDto;
import com.spring.studentmanagement.exceptions.AuthenticationException;
import com.spring.studentmanagement.models.AppUser;
import com.spring.studentmanagement.models.Role;
import com.spring.studentmanagement.security.interfaces.SecurityService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AuthController tests")
class AuthControllerTest {
    private static final String ATTR_ERROR_MESSAGE = "errorMessage";
    private static final String USER_PRINCIPAL = "userPrincipal";
    private static final String PARAM_USERNAME_OR_EMAIL = "usernameOrEmail";
    private static final String PARAM_PASSWORD = "password";

    @Mock
    private SecurityService securityService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private AuthController authController;

    @Test
    @DisplayName("Should return an error when an exception occurs during logout")
    void logoutWhenExceptionOccursThenReturnError() {
        doThrow(new RuntimeException("Internal server error occurred"))
                .when(securityService)
                .logout(request);

        String result = authController.logout(request, redirectAttributes);

        assertEquals("redirect:/errors/error-500", result);
        verify(redirectAttributes, times(1))
                .addFlashAttribute("errorMessage", "Internal server error occurred");
    }

    @Test
    @DisplayName("Should fail to register a new user and return an internal server error")
    void registerUserWithInvalidDataThenReturnError() {
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
        signUpRequestDto.setUsername("testuser");
        signUpRequestDto.setEmail("testuser@example.com");
        signUpRequestDto.setPassword("password");
        signUpRequestDto.setConfirmPassword("password");
        signUpRequestDto.setFirstName("Test");
        signUpRequestDto.setLastName("User");
        signUpRequestDto.setRoleId(1L);

        when(securityService.signUp(signUpRequestDto))
                .thenThrow(new RuntimeException("Failed to register user"));

        String result = authController.registerUser(signUpRequestDto, redirectAttributes);

        assertEquals("redirect:/errors/error-500", result);
        verify(securityService, times(1)).signUp(signUpRequestDto);
        assertThat(redirectAttributes.getFlashAttributes().get("errorMessage"))
                .isNull();
    }

    @Test
    @DisplayName("Should register a new user and redirect to the login page with a success message")
    void registerUserWithValidData() { // Create a SignUpRequestDto object with valid data
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
        signUpRequestDto.setUsername("testuser");
        signUpRequestDto.setEmail("testuser@example.com");
        signUpRequestDto.setPassword("password");
        signUpRequestDto.setConfirmPassword("password");
        signUpRequestDto.setFirstName("Test");
        signUpRequestDto.setLastName("User");
        signUpRequestDto.setRoleId(1L);

        // Create an AppUser object with the same data as the SignUpRequestDto object
        Role role = Role.builder().roleId(signUpRequestDto.getRoleId()).build();
        AppUser appUser =
                AppUser.builder()
                        .username(signUpRequestDto.getUsername())
                        .email(signUpRequestDto.getEmail())
                        .password(signUpRequestDto.getPassword())
                        .firstName(signUpRequestDto.getFirstName())
                        .lastName(signUpRequestDto.getLastName())
                        .role(role)
                        .isEnabled(true)
                        .dateAdded(LocalDateTime.now())
                        .build();

        when(securityService.signUp(signUpRequestDto)).thenReturn(appUser);

        String result = authController.registerUser(signUpRequestDto, redirectAttributes);

        verify(securityService, times(1)).signUp(signUpRequestDto);

        verify(redirectAttributes, times(1))
                .addFlashAttribute("registerStatusMsg", "Account successfully created!");
        assertEquals("redirect:/auth/login", result);
    }

    @Test
    @DisplayName(
            "Should not authenticate and redirect to error 500 page when an internal server error occurs")
    void authenticateWhenInternalServerErrorOccursThenRedirectToError500() throws AuthenticationException {
        LoginRequest loginRequest =
                LoginRequest.builder().usernameOrEmail("john.doe").password("password").build();

        when(request.getParameter(PARAM_USERNAME_OR_EMAIL))
                .thenReturn(loginRequest.usernameOrEmail());
        when(request.getParameter(PARAM_PASSWORD)).thenReturn(loginRequest.password());
        when(securityService.signIn(loginRequest))
                .thenThrow(new RuntimeException("Internal server error"));

        String result = authController.authenticate(redirectAttributes);

        assertEquals("redirect:/errors/error-500", result);
        assertThat(redirectAttributes.getFlashAttributes().get("errorMessage"))
                .isNull();

    }

    @Test
    @DisplayName(
            "Should not authenticate and redirect to error 401 page when credentials are invalid")
    void authenticateWhenCredentialsAreInvalidThenRedirectToError401() throws AuthenticationException {
        LoginRequest loginRequest = new LoginRequest("testuser", "testpassword");
        when(request.getParameter(PARAM_USERNAME_OR_EMAIL))
                .thenReturn(loginRequest.usernameOrEmail());
        when(request.getParameter(PARAM_PASSWORD)).thenReturn(loginRequest.password());
        when(securityService.signIn(loginRequest))
                .thenThrow(new AuthenticationException("Invalid credentials"));

        String result = authController.authenticate(redirectAttributes);

        assertEquals("redirect:/errors/error-401", result);
        verify(redirectAttributes, times(1))
                .addFlashAttribute(ATTR_ERROR_MESSAGE, "Invalid credentials");
        verify(securityService, times(1)).signIn(loginRequest);
    }

    @Test
    @DisplayName("Should authenticate and redirect to users page when credentials are valid")
    void authenticateWhenCredentialsAreValid() throws AuthenticationException {
        LoginRequest loginRequest = new LoginRequest("testuser", "testpassword");
        AppUser appUser =
                AppUser.builder()
                        .userId(1L)
                        .role(Role.builder().roleId(1L).roleName("ROLE_USER").build())
                        .firstName("Test")
                        .lastName("User")
                        .username("testuser")
                        .password("testpassword")
                        .email("testuser@example.com")
                        .isEnabled(true)
                        .dateAdded(LocalDateTime.now())
                        .build();

        when(request.getParameter(PARAM_USERNAME_OR_EMAIL))
                .thenReturn(loginRequest.usernameOrEmail());
        when(request.getParameter(PARAM_PASSWORD)).thenReturn(loginRequest.password());
        when(securityService.signIn(loginRequest)).thenReturn(appUser);

        String result = authController.authenticate(redirectAttributes);

        assertEquals("redirect:/users", result);
        verify(redirectAttributes, times(1)).addFlashAttribute(USER_PRINCIPAL, appUser);
    }
}