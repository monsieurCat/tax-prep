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
        Principal principal = () -> "testUser";

        AppUser user = new AppUser();
        user.setUsername("testUser");
        when(userService.loadUserByUsername("testUser")).thenReturn(user);

        ResponseEntity<?> responseEntity = userController.findUsername(principal);

        verify(userService).loadUserByUsername("testUser");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(Collections.singletonMap("username", "testUser"), responseEntity.getBody());
    }

    @Test
    public void testFindUserInfo() {
        Principal principal = () -> "testUser";

        AppUser user = new AppUser();
        user.setUsername("testUser");
        when(userService.loadUserByUsername("testUser")).thenReturn(user);

        ResponseEntity<?> responseEntity = userController.findUserInfo(principal);

        verify(userService).loadUserByUsername("testUser");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateUser() {
        Principal principal = () -> "testUser";

        AppUserDTO userDTO = new AppUserDTO();
        userDTO.setUsername("testUser");
        when(userService.updateUser("testUser", userDTO)).thenReturn(userDTO);

        ResponseEntity<?> responseEntity = userController.updateUser(principal, userDTO);

        verify(userService).updateUser("testUser", userDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userDTO, responseEntity.getBody());
    }
}
