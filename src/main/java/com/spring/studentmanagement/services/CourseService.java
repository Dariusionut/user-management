package com.spring.studentmanagement.services;

import com.spring.studentmanagement.exceptions.CourseNotFoundException;
import com.spring.studentmanagement.exceptions.UserNotFoundException;
import com.spring.studentmanagement.models.AppUser;
import com.spring.studentmanagement.models.Course;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CourseService {
    List<Course> findAllCourses();

    void deleteCourseById(Long courseId);

    Course getCourseById(Long courseId) throws CourseNotFoundException;

    Course saveCourse(Course course);

}