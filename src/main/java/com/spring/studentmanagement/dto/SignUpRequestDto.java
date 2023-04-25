package com.spring.studentmanagement.dto;

import com.spring.studentmanagement.models.AppUser;
import com.spring.studentmanagement.models.Role;
import lombok.Data;
import lombok.ToString;

/**
 * Created at 4/25/2023 by Darius
 **/
@Data
public class SignUpRequestDto {

    private String username;
    private String email;
    private @ToString.Exclude String password;
    private @ToString.Exclude String confirmPassword;
    private String firstName;
    private String lastName;
    private long roleId;

    private boolean is_enabled;

    public boolean passwordMatches() throws NullPointerException {
        return this.password.equals(this.confirmPassword);
    }

    public AppUser toUser() {
        final Role role = Role.builder().roleId(this.roleId).build();
        return AppUser.builder()
                .username(this.username)
                .email(this.email)
                .password(this.password)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .role(role)
                .isEnabled(true)
                .build();

    }

}
