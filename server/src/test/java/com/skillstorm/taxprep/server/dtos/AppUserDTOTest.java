package com.skillstorm.taxprep.server.dtos;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class AppUserDTOTest {

    @Test
    void testGettersAndSetters() {
        AppUserDTO appUserDTO = new AppUserDTO();

        appUserDTO.setFirstName("John");
        appUserDTO.setMiddleName("Doe");
        appUserDTO.setLastName("Smith");
        appUserDTO.setEmail("john@example.com");
        appUserDTO.setSsn("123-45-6789");
        appUserDTO.setUsername("johndoe");
        appUserDTO.setBirthday(LocalDate.of(1990, 5, 15));
        appUserDTO.setRole("USER");

        assertEquals("John", appUserDTO.getFirstName());
        assertEquals("Doe", appUserDTO.getMiddleName());
        assertEquals("Smith", appUserDTO.getLastName());
        assertEquals("john@example.com", appUserDTO.getEmail());
        assertEquals("123-45-6789", appUserDTO.getSsn());
        assertEquals("johndoe", appUserDTO.getUsername());
        assertEquals(LocalDate.of(1990, 5, 15), appUserDTO.getBirthday());
        assertEquals("USER", appUserDTO.getRole());
    }

    @Test
    void testDefaultConstructor() {
        AppUserDTO appUserDTO = new AppUserDTO();

        assertNull(appUserDTO.getFirstName());
        assertNull(appUserDTO.getMiddleName());
        assertNull(appUserDTO.getLastName());
        assertNull(appUserDTO.getEmail());
        assertNull(appUserDTO.getSsn());
        assertNull(appUserDTO.getUsername());
        assertNull(appUserDTO.getBirthday());
        assertNull(appUserDTO.getRole());
    }

    @Test
    void testSettersWithNullValues() {
        AppUserDTO appUserDTO = new AppUserDTO();

        appUserDTO.setFirstName(null);
        appUserDTO.setMiddleName(null);
        appUserDTO.setLastName(null);
        appUserDTO.setEmail(null);
        appUserDTO.setSsn(null);
        appUserDTO.setUsername(null);
        appUserDTO.setBirthday(null);
        appUserDTO.setRole(null);

        assertNull(appUserDTO.getFirstName());
        assertNull(appUserDTO.getMiddleName());
        assertNull(appUserDTO.getLastName());
        assertNull(appUserDTO.getEmail());
        assertNull(appUserDTO.getSsn());
        assertNull(appUserDTO.getUsername());
        assertNull(appUserDTO.getBirthday());
        assertNull(appUserDTO.getRole());
    }
}
