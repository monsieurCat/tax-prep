package com.skillstorm.taxprep.server.dtos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChangePasswordDTOTest {

    @Test
    void testGettersAndSetters() {
        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();

        changePasswordDTO.setCurrentPassword("oldPassword");
        changePasswordDTO.setNewPassword("newPassword");

        assertEquals("oldPassword", changePasswordDTO.getCurrentPassword());
        assertEquals("newPassword", changePasswordDTO.getNewPassword());
    }

    @Test
    void testDefaultConstructor() {
        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();

        assertNull(changePasswordDTO.getCurrentPassword());
        assertNull(changePasswordDTO.getNewPassword());
    }

    @Test
    void testSettersWithNullValues() {
        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();

        changePasswordDTO.setCurrentPassword(null);
        changePasswordDTO.setNewPassword(null);

        assertNull(changePasswordDTO.getCurrentPassword());
        assertNull(changePasswordDTO.getNewPassword());
    }
}
