package com.spring.studentmanagement.services;

import com.spring.studentmanagement.StudentManagementTest;
import com.spring.studentmanagement.models.Address;
import com.spring.studentmanagement.models.AppUser;
import com.spring.studentmanagement.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.spring.studentmanagement.utils.EntityFactoryTestUtils.getUserEntityList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest implements StudentManagementTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("Should return all users when findAllUsers is called")
    void findAllUsersReturnsAllUsers() {
        List<AppUser> expectedUsers = getUserEntityList();

        when(userRepository.findAll()).thenReturn(expectedUsers);

        List<AppUser> actualUsers = userService.findAllUsers();

        assertEquals(expectedUsers, actualUsers, "The returned users should match the expected users");
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void saveUserTest() {
        final Address address = Address.builder()
                .id(1L)
                .city("city")
                .doorNumber("1A")
                .additionalDetails("additionalDetails")
                .build();
        final AppUser userToBeSaved = AppUser.builder()
                .firstName("firstName")
                .lastName("lastName")
                .isEnabled(true)
                .email("email@test.com")
                .username("username")
                .address(address)
                .build();

        Mockito.when(this.userRepository.save(any(AppUser.class))).thenReturn(userToBeSaved);

        AppUser savedUser = this.userService.saveUser(userToBeSaved);

        assertNotNull(savedUser);
        Mockito.verify(this.userRepository).save(any(AppUser.class));


    }
}
