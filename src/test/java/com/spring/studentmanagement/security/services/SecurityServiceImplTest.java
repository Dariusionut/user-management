package com.spring.studentmanagement.security.services;

import com.spring.studentmanagement.models.AppUser;
import com.spring.studentmanagement.security.utils.Security;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.spring.studentmanagement.security.utils.SecurityConstants.USER_PRINCIPAL;
import static org.mockito.Mockito.*;

@Security
@ExtendWith(MockitoExtension.class)
@DisplayName("SecurityServiceImpl tests")
@Slf4j
class SecurityServiceImplTest {
    @InjectMocks
    private SecurityServiceImpl securityService;

    @Test
    @DisplayName("Should remove user principal and invalidate session when a user is logged in")
    void logoutWhenUserIsLoggedIn() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        AppUser userPrincipal = mock(AppUser.class);

        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute(USER_PRINCIPAL)).thenReturn(userPrincipal);

        securityService.logout(request);

        verify(session, times(1)).removeAttribute(USER_PRINCIPAL);
        verify(session, times(1)).invalidate();
        verify(userPrincipal, times(1)).getUsername();
        verifyNoMoreInteractions(session, userPrincipal);
    }
}