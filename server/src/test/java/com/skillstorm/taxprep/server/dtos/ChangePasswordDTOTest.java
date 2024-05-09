package com.skillstorm.taxprep.server.dtos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChangePasswordDTOTest {

    @Test
    void testGettersAndSetters() {
        // Arrange
        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();

        // Act
        changePasswordDTO.setCurrentPassword("oldPassword");
        changePasswordDTO.setNewPassword("newPassword");

        // Assert
        assertEquals("oldPassword", changePasswordDTO.getCurrentPassword());
        assertEquals("newPassword", changePasswordDTO.getNewPassword());
    }

    @Test
    void testDefaultConstructor() {
        // Arrange & Act
        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();

        // Assert
        assertNull(changePasswordDTO.getCurrentPassword());
        assertNull(changePasswordDTO.getNewPassword());
    }

    @Test
    void testSettersWithNullValues() {
        // Arrange
        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();

        // Act
        changePasswordDTO.setCurrentPassword(null);
        changePasswordDTO.setNewPassword(null);

        // Assert
        assertNull(changePasswordDTO.getCurrentPassword());
        assertNull(changePasswordDTO.getNewPassword());
    }
}
