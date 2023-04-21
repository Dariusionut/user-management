package com.spring.studentmanagement.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created at 4/19/2023 by Darius
 **/

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "app_user", schema = "public")
@Entity(name = "AppUser")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_app_user")
    @SequenceGenerator(name = "seq_app_user", allocationSize = 1)
    private Long userId;

    @ManyToOne(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_role", nullable = false)
    private Role role;

    @Column(name = "first_name", length = 65, nullable = false)
    private String first_name;

    @Column(name = "last_name", length = 65, nullable = false)
    private String last_name;

    @Column(name = "username", length = 45, unique = true, nullable = false)
    private String username;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "email", length = 65, unique = true, nullable = false)
    private String email;

    @Column(name = "is_enabled", nullable = false, columnDefinition="boolean DEFAULT true")
    private boolean is_enabled;

    private LocalDateTime dateAdded;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AppUser appUser = (AppUser) o;
        return userId != null && Objects.equals(userId, appUser.userId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
