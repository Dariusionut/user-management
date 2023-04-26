package com.spring.studentmanagement.models;

import jakarta.persistence.*;
import lombok.*;
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "course", schema = "public")
@Entity(name = "Course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_course")
    @SequenceGenerator(name = "seq_course", allocationSize = 1)
    @Column(name = "course_id", columnDefinition = "UNIQUE NOT NULL DEFAULT nextval('seq_course')")
    private Long courseId;

    @Column(name = "course_name", length = 65, nullable = false)
    private String courseName;

    @ManyToOne(targetEntity = CourseCategory.class, fetch = FetchType.EAGER)
    @JoinColumn(
            name = "fk_course_category",
            nullable = false,
            columnDefinition = "BIGINT DEFAULT 1",
            foreignKey = @ForeignKey(name = "course_fk_course_category")
    )
    private CourseCategory courseCategory;
}
