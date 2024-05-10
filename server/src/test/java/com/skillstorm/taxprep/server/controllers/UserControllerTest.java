package com.skillstorm.taxprep.server.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.skillstorm.taxprep.server.models.AppUser;
import com.skillstorm.taxprep.server.dtos.AppUserDTO;
import com.skillstorm.taxprep.server.services.UserService;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindUsername() {
        // Mock Principal
        Principal principal = () -> "testUser";

        // Mock data
        AppUser user = new AppUser();
        user.setUsername("testUser");
        when(userService.loadUserByUsername("testUser")).thenReturn(user);

        // Invoke controller method
        ResponseEntity<?> responseEntity = userController.findUsername(principal);

        // Verify service method invocation
        verify(userService).loadUserByUsername("testUser");

        // Assert response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(Collections.singletonMap("username", "testUser"), responseEntity.getBody());
    }

    @Test
    public void testFindUserInfo() {
        // Mock Principal
        Principal principal = () -> "testUser";

        // Mock data
        AppUser user = new AppUser();
        user.setUsername("testUser");
        when(userService.loadUserByUsername("testUser")).thenReturn(user);

        // Invoke controller method
        ResponseEntity<?> responseEntity = userController.findUserInfo(principal);

        // Verify service method invocation
        verify(userService).loadUserByUsername("testUser");

        // Assert response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        // Add assertions for DTO mapping if applicable
    }

    @Test
    public void testUpdateUser() {
        // Mock Principal
        Principal principal = () -> "testUser";

        // Mock data
        AppUserDTO userDTO = new AppUserDTO();
        userDTO.setUsername("testUser");
        when(userService.updateUser("testUser", userDTO)).thenReturn(userDTO);

        // Invoke controller method
        ResponseEntity<?> responseEntity = userController.updateUser(principal, userDTO);

        // Verify service method invocation
        verify(userService).updateUser("testUser", userDTO);

        // Assert response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userDTO, responseEntity.getBody());
    }

    // Add more tests for other controller methods as needed
}
