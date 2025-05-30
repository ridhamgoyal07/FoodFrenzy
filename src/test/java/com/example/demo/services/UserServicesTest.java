package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

class UserServicesTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServices userServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUser_ShouldReturnListOfUsers() {
        // Arrange
        List<User> expectedUsers = Arrays.asList(new User(), new User());
        when(userRepository.findAll()).thenReturn(expectedUsers);

        // Act
        List<User> actualUsers = userServices.getAllUser();

        // Assert
        assertEquals(expectedUsers, actualUsers);
        verify(userRepository).findAll();
    }

    @Test
    void getUser_WithValidId_ShouldReturnUser() {
        // Arrange
        User expectedUser = new User();
        when(userRepository.findById(1)).thenReturn(Optional.of(expectedUser));

        // Act
        User actualUser = userServices.getUser(1);

        // Assert
        assertEquals(expectedUser, actualUser);
        verify(userRepository).findById(1);
    }

    @Test
    void getUserByEmail_WithValidEmail_ShouldReturnUser() {
        // Arrange
        User expectedUser = new User();
        String email = "test@example.com";
        when(userRepository.findUserByUemail(email)).thenReturn(expectedUser);

        // Act
        User actualUser = userServices.getUserByEmail(email);

        // Assert
        assertEquals(expectedUser, actualUser);
        verify(userRepository).findUserByUemail(email);
    }

    @Test
    void updateUser_ShouldUpdateUserWithCorrectId() {
        // Arrange
        User user = new User();
        int id = 1;

        // Act
        userServices.updateUser(user, id);

        // Assert
        assertEquals(id, user.getU_id());
        verify(userRepository).save(user);
    }

    @Test
    void deleteUser_ShouldCallRepositoryDelete() {
        // Arrange
        int id = 1;

        // Act
        userServices.deleteUser(id);

        // Assert
        verify(userRepository).deleteById(id);
    }

    @Test
    void addUser_ShouldSaveUser() {
        // Arrange
        User user = new User();

        // Act
        userServices.addUser(user);

        // Assert
        verify(userRepository).save(user);
    }

    
}
