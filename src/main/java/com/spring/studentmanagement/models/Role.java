package com.spring.studentmanagement.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

/**
 * Created at 4/19/2023 by Darius
 **/

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role", schema = "public")
@Entity(name = "Role")
@Builder
@Cacheable
public class Role {

    @Id
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name", length = 45, unique = true, nullable = false)
    private String roleName;
    @OneToMany(mappedBy = "role")
    private Collection<AppUser> appUser;

    public Collection<AppUser> getAppUser() {
        return appUser;
    }

    public void setAppUser(Collection<AppUser> appUser) {
        this.appUser = appUser;
    }
}
