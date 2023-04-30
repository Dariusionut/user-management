package com.spring.studentmanagement.security.interfaces;

import java.security.Principal;
import java.time.LocalDateTime;

/**
 * Created at 4/30/2023 by Darius
 **/
public interface AppPrincipal extends Principal {
    String getAuthority();
    LocalDateTime getDateAdded();
}
