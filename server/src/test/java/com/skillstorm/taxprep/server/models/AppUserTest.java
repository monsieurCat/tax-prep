package com.skillstorm.taxprep.server.models;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class AppUserTest {

  @Test
  public void testBuilder() {

    AppUser user = new AppUser.AppUserBuilder()
            .id(1)
            .firstName("John")
            .lastName("Doe")
            .username("johndoe")
            .password("password123")
            .email("john@example.com")
            .birthday(LocalDate.of(1990, 5, 15))
            .role("USER")
            .build();

    assertNotNull(user);
    assertEquals(1, user.getId());
    assertEquals("John", user.getFirstName());
    assertEquals("Doe", user.getLastName());
    assertEquals("johndoe", user.getUsername());
    assertEquals("password123", user.getPassword());
    assertEquals("john@example.com", user.getEmail());
    assertEquals(LocalDate.of(1990, 5, 15), user.getBirthday());
    assertEquals("USER", user.getRole());
  }

    @Test
    public void testGettersAndSetters() {
        AppUser user = new AppUser();

        user.setId(1);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUsername("johndoe");
        user.setPassword("password123");
        user.setEmail("john@example.com");
        user.setBirthday(LocalDate.of(1990, 5, 15));
        user.setRole("USER");

        assertEquals(1, user.getId());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("johndoe", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("john@example.com", user.getEmail());
        assertEquals(LocalDate.of(1990, 5, 15), user.getBirthday());
        assertEquals("USER", user.getRole());
    }

    @Test
    public void testEqualsAndHashCode() {
        AppUser user1 = new AppUser();
        user1.setId(1);
        user1.setUsername("johndoe");

        AppUser user2 = new AppUser();
        user2.setId(1);
        user2.setUsername("johndoe");

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());

        user2.setUsername("janedoe");

        assertNotEquals(user1, user2);
        assertNotEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    public void testSpringSecurityUserDetailsMethods() {
        AppUser user = new AppUser();
        user.setRole("USER");

        // Test getAuthorities()
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        assertEquals(1, authorities.size());
        assertEquals(authorities.iterator().next(), new SimpleGrantedAuthority("ROLE_USER"));
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_USER")));

        // Test other UserDetails methods
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());
        assertTrue(user.isEnabled());
    }
}