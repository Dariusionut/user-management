package com.spring.studentmanagement.repositories;

import com.spring.studentmanagement.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created at 4/25/2023 by Darius
 **/
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
