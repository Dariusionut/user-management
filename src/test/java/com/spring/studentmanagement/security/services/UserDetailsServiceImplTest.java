package com.spring.studentmanagement.security.services;

import com.spring.studentmanagement.exceptions.AuthenticationException;
import com.spring.studentmanagement.models.AppUser;
import com.spring.studentmanagement.repositories.UserRepository;
import com.spring.studentmanagement.utils.EntityFactoryTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Created at 5/3/2023 by Darius
 **/

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {
    private @Mock UserRepository userRepository;
    private @InjectMocks UserDetailsServiceImpl userDetailsService;

    private String usernameOrEmail;

    @BeforeEach
    void setUp() {
        this.usernameOrEmail = "MockUsernameOrEmail";
    }

    @Test
    void loadByUsernameOrEmail() throws AuthenticationException {
        final AppUser expectedAppUser = EntityFactoryTestUtils.USER_TEST_ONE;

        final Optional<AppUser> optionalAppUser = Optional.of(expectedAppUser);

        when(this.userRepository.findByUsernameOrEmail(this.usernameOrEmail))
                .thenReturn(optionalAppUser);

        final AppUser actualUser = this.userDetailsService.loadByUsernameOrEmail(this.usernameOrEmail);

        assertEquals(expectedAppUser, actualUser, "Users are not equals");

        assertNotNull(actualUser.getUsername());
        assertNotNull(actualUser.getFirstName());
        assertNotNull(actualUser.getLastName());

    }

    @Test
    void loadByUsernameOrEmailThrowsAuthenticationExceptionTest() {

        when(this.userRepository.findByUsernameOrEmail(this.usernameOrEmail)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class,
                () -> this.userDetailsService.loadByUsernameOrEmail(this.usernameOrEmail)
        );

        assertTrue(exception instanceof AuthenticationException);

        AtomicReference<AuthenticationException> authExceptionRef = new AtomicReference<>();

        assertDoesNotThrow(() -> {
            AuthenticationException authException = (AuthenticationException) exception;
            authExceptionRef.set(authException);
        });

        assertNotNull(authExceptionRef.get());

        AuthenticationException authException = authExceptionRef.get();

        assertEquals("Username not found", authException.getMessage());

    }


    @Disabled(value = "Test under construction")
    @Test
    void brokenExampleTest() throws AuthenticationException {
        final AppUser actualUser = this.userDetailsService.loadByUsernameOrEmail(this.usernameOrEmail);

        assertNotNull(actualUser);
    }
}
