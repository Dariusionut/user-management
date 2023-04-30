package com.spring.studentmanagement.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.security.Principal;

/**
 * Created at 4/30/2023 by Darius
 **/
public class AppHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private final Principal principal;

    public AppHttpServletRequestWrapper(HttpServletRequest request, Principal principal) {
        super(request);
        this.principal = principal;
    }

    @Override
    public Principal getUserPrincipal() {
        return this.principal;
    }
}
