package com.spring.studentmanagement.repositories;

import com.spring.studentmanagement.models.Course;
import com.spring.studentmanagement.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
