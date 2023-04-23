package com.spring.studentmanagement;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created at 4/23/2023 by Darius
 **/
@ActiveProfiles(value = "test")
@ContextConfiguration(classes = {StudentManagementApplication.class})
public interface StudentManagementTest {
}
