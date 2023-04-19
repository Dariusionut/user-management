package com.spring.studentmanagement.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created at 4/19/2023 by Darius
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role", schema = "public")
@Entity(name = "Role")
public class Role {

    @Id
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name", length = 45, unique = true, nullable = false)
    private String roleName;
}
