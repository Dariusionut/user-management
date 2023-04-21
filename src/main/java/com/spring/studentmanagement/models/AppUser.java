package com.spring.studentmanagement.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created at 4/19/2023 by Darius
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "name", length = 65, nullable = false)
    private String name;
    @Column(name = "username", length = 45)
    private String username;

    @Column(name = "password", length = 100)
    private String password;

    private LocalDateTime dateAdded;
}
