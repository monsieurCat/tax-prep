package com.skillstorm.taxprep.server.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.skillstorm.taxprep.server.models.AppUser;
import com.skillstorm.taxprep.server.services.UserService;

import java.security.Principal;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @Test
    void testHomeAuthenticated() {
        // Mock principal
        Principal principal = () -> "testUser";

        // Mock userService behavior
        AppUser testUser = new AppUser.AppUserBuilder()
                .username("testUser")
                .password("password")
                .role("USER")
                .build();

        when(userService.loadUserByUsername("testUser")).thenReturn(testUser);

        // Call controller method
        ResponseEntity<?> response = userController.home(principal);

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.singletonMap("username", "testUser"), response.getBody());
    }

    @Test
    void testHomeUnauthenticated() {
        // Mock principal
        Principal principal = null;

        // Call controller method
        ResponseEntity<?> response = userController.home(principal);

        // Verify response
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals(Collections.singletonMap("error", "User is not authenticated"), response.getBody());
    }

    @Test
    void testFindUserById() {
        // Mock principal
        Principal principal = () -> "testUser";

        // Mock userService behavior
        AppUser testUser = new AppUser.AppUserBuilder()
                .id(1)
                .username("testUser")
                .password("password")
                .role("USER")
                .build();
        when(userService.loadUserByUsername("testUser")).thenReturn(testUser);

        // Call controller method
        ResponseEntity<?> response = userController.findUserById(principal);

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    void testUpdateUser() {
        // Mock principal
        Principal principal = () -> "testUser";

        // Mock userService behavior
        AppUser testUser = new AppUser.AppUserBuilder()
                .id(1)
                .username("testUser")
                .password("password")
                .role("USER")
                .build();
        when(userService.loadUserByUsername("testUser")).thenReturn(testUser);
        when(userService.register(testUser)).thenReturn(testUser);

        // Call controller method
        ResponseEntity<?> response = userController.updateUser(principal, testUser);

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }
}
