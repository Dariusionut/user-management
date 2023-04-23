package com.spring.studentmanagement.models;

import jakarta.persistence.*;
import lombok.*;

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
}
