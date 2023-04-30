package com.spring.studentmanagement.security;

import com.spring.studentmanagement.security.interfaces.AppPrincipal;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created at 4/30/2023 by Darius
 **/
@RequiredArgsConstructor
public class AppPrincipalImpl implements AppPrincipal {

    private final String name;
    private final String authority;
    private final LocalDateTime dateAdded;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    @Override
    public LocalDateTime getDateAdded() {
        return this.dateAdded;
    }


}
