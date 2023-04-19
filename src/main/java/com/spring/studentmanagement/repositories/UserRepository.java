package com.spring.studentmanagement.repositories;

import com.spring.studentmanagement.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created at 4/19/2023 by Darius
 **/

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
}
