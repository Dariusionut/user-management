package com.spring.studentmanagement.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
    private String firstName;

    @Column(name = "last_name", length = 65, nullable = false)
    private String lastName;

    @Column(name = "username", length = 45, unique = true, nullable = false)
    private String username;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "email", length = 65, unique = true, nullable = false)
    private String email;

    @Column(name = "is_enabled", nullable = false, columnDefinition = "boolean DEFAULT true")
    private boolean isEnabled;

    private LocalDateTime dateAdded;

}