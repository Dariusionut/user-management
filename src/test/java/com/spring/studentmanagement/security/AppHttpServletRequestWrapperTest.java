package com.spring.studentmanagement.security;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created at 4/30/2023 by Darius
 **/
@ExtendWith(MockitoExtension.class)
class AppHttpServletRequestWrapperTest {
    @Mock
    private HttpServletRequest servletRequestMock;
    @Mock
    private Principal principalMock;
    private AppHttpServletRequestWrapper httpServletRequestWrapper;

    @BeforeEach
    void setUp() {
//        Inject mocks (dependency mocks)
        this.httpServletRequestWrapper = new AppHttpServletRequestWrapper(this.servletRequestMock, this.principalMock);
    }

    @Test
    void getUserPrincipal() {

        assertEquals(this.principalMock, httpServletRequestWrapper.getUserPrincipal());
    }
}
