package com.spring.studentmanagement.models;

import com.spring.studentmanagement.utils.EntityFactoryTestUtils;
import org.junit.jupiter.api.*;

/**
 * Created at 5/3/2023 by Darius
 **/
class RoleTest {

    private Role roleTest;
    private Role roleTestTwo;
    @BeforeEach
    void setUp() {
        this.roleTest = Role.builder()
                .roleId(Long.MAX_VALUE)
                .roleName("ROLE_TEST")
                .build();

        this.roleTestTwo = EntityFactoryTestUtils.ROLE_VISITOR;
    }

    @Test
    void roleTestExample(){
        Assertions.assertNotEquals(roleTest, roleTestTwo);
    }
}
