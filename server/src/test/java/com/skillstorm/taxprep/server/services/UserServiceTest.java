package com.skillstorm.taxprep.server.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.skillstorm.taxprep.server.exceptions.UsernameAlreadyExistsException;
import com.skillstorm.taxprep.server.models.AppUser;
import com.skillstorm.taxprep.server.repositories.UserRepository;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void testLoadUserByUsername_UserFound() {
        // Mock repository behavior
        AppUser testUser = new AppUser.AppUserBuilder()
                .username("testUser")
                .password("password")
                .role("USER")
                .build();
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(testUser));

        // Call service method
        AppUser loadedUser = userService.loadUserByUsername("testUser");

        // Verify that user is loaded correctly
        assertNotNull(loadedUser);
        assertEquals("testUser", loadedUser.getUsername());
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        // Mock repository behavior
        when(userRepository.findByUsername("nonExistingUser")).thenReturn(Optional.empty());

        // Call service method and expect UsernameNotFoundException
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("nonExistingUser"));
    }

    @Test
    void testRegister_NewUser() {
        // Mock repository behavior
        AppUser newUser = new AppUser.AppUserBuilder()
                .username("newUser")
                .password("password")
                .role("USER")
                .build();
        when(userRepository.findByUsername("newUser")).thenReturn(Optional.empty());
        when(userRepository.save(newUser)).thenReturn(newUser);

        // Call service method
        AppUser registeredUser = userService.register(newUser);

        // Verify that user is registered correctly
        assertNotNull(registeredUser);
        assertEquals("newUser", registeredUser.getUsername());
    }

    @Test
    void testRegister_UserAlreadyExists() {
        // Mock repository behavior
        AppUser existingUser = new AppUser.AppUserBuilder()
                .username("existingUser")
                .password("password")
                .role("USER")
                .build();
        when(userRepository.findByUsername("existingUser")).thenReturn(Optional.of(existingUser));

        // Call service method and expect UsernameAlreadyExistsException
        assertThrows(UsernameAlreadyExistsException.class, () -> userService.register(existingUser));
    }

    @Test
    void testRegisterAdmin() {
        // Mock repository behavior
        AppUser newUser = new AppUser.AppUserBuilder()
                .username("newAdmin")
                .password("password")
                .role("ADMIN")
                .build();
        when(userRepository.findByUsername("newAdmin")).thenReturn(Optional.empty());

        // Call service method
        userService.registerAdmin(newUser);

        // Verify that user is registered as admin
        assertEquals("ADMIN", newUser.getRole());
        verify(userRepository, times(1)).save(newUser);
    }

    @Test
    void testDeleteUser() {
        // Mock repository behavior
        AppUser userToDelete = new AppUser.AppUserBuilder()
                .id(1)
                .username("userToDelete")
                .password("password")
                .role("USER")
                .build();

        // Call service method
        userService.deleteUser(userToDelete);

        // Verify that user is deleted
        verify(userRepository, times(1)).delete(userToDelete);
    }

    @Test
    void testCountAllUsers() {
        // Mock repository behavior
        when(userRepository.count()).thenReturn(5L);

        // Call service method
        long userCount = userService.countAllUsers();

        // Verify that correct user count is returned
        assertEquals(5L, userCount);
    }
}
