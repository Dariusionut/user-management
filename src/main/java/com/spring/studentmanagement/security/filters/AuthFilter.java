package com.spring.studentmanagement.security.filters;

import com.spring.studentmanagement.models.AppUser;
import com.spring.studentmanagement.security.AppHttpServletRequestWrapper;
import com.spring.studentmanagement.security.AppPrincipalImpl;
import com.spring.studentmanagement.security.interfaces.AppPrincipal;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Created at 4/25/2023 by Darius
 **/

@Component
@ConditionalOnProperty(name = "security.enabled", havingValue = "true")
@Slf4j
public class AuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        AppUser user = (AppUser) session.getAttribute("userPrincipal");
        if (user == null) {
            response.sendRedirect("/user-management/errors/error-401");
            return;
        }

        final AppPrincipal appPrincipal = new AppPrincipalImpl(user.getUsername(), user.getRole().getRoleName(), user.getDateAdded());
        final AppHttpServletRequestWrapper requestWrapper = new AppHttpServletRequestWrapper(request, appPrincipal);

        filterChain.doFilter(requestWrapper, response);
    }

}
