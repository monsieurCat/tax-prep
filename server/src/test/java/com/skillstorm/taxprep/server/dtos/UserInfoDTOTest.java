package com.skillstorm.taxprep.server.dtos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class UserInfoDTOTest {

    @Test
    void testGettersAndSetters() {
        // Arrange
        UserInfoDTO userInfoDTO = new UserInfoDTO();

        // Act
        userInfoDTO.setFirstName("John");
        userInfoDTO.setMiddleName("Doe");
        userInfoDTO.setLastName("Smith");
        userInfoDTO.setEmail("john.smith@example.com");
        userInfoDTO.setSsn("123-45-6789");
        userInfoDTO.setUsername("johnsmith");
        userInfoDTO.setBirthday(LocalDate.of(1990, 5, 15));
        userInfoDTO.setRole("USER");
        userInfoDTO.setStreet1("123 Main St");
        userInfoDTO.setStreet2("Apt 101");
        userInfoDTO.setCity("Anytown");
        userInfoDTO.setState("NY");
        userInfoDTO.setPostalCode("12345");

        // Assert
        assertEquals("John", userInfoDTO.getFirstName());
        assertEquals("Doe", userInfoDTO.getMiddleName());
        assertEquals("Smith", userInfoDTO.getLastName());
        assertEquals("john.smith@example.com", userInfoDTO.getEmail());
        assertEquals("123-45-6789", userInfoDTO.getSsn());
        assertEquals("johnsmith", userInfoDTO.getUsername());
        assertEquals(LocalDate.of(1990, 5, 15), userInfoDTO.getBirthday());
        assertEquals("USER", userInfoDTO.getRole());
        assertEquals("123 Main St", userInfoDTO.getStreet1());
        assertEquals("Apt 101", userInfoDTO.getStreet2());
        assertEquals("Anytown", userInfoDTO.getCity());
        assertEquals("NY", userInfoDTO.getState());
        assertEquals("12345", userInfoDTO.getPostalCode());
    }
}
