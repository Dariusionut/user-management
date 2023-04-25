package com.spring.studentmanagement.security.filters;

import com.spring.studentmanagement.models.AppUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Created at 4/25/2023 by Darius
 **/

@Component
@Slf4j
public class AuthFilter extends OncePerRequestFilter {

    @Value("${security.enabled}")
    private boolean securityEnabled;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        final String uri = request.getRequestURI();
        return uri.equals("/user-management/") ||
                uri.startsWith("/user-management/styles") ||
                uri.startsWith("/user-management/static") ||
                uri.startsWith("/user-management/scripts") ||
                uri.startsWith("/user-management/images") ||
                uri.startsWith("/user-management/webjars") ||
                uri.startsWith("/user-management/home") ||
                uri.startsWith("/user-management/auth") ||
                uri.startsWith("/user-management/errors");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (this.securityEnabled) {
            final HttpSession session = request.getSession();
            AppUser user = (AppUser) session.getAttribute("userPrincipal");
            if (user == null) {
                response.sendRedirect("/user-management/errors/error-401");
            }
            log.warn("user = {}", user);
            log.warn("Filter  url = {}", request.getRequestURI());
        } else {
            log.warn("Security mode is disabled!");
        }
        filterChain.doFilter(request, response);
    }
}
