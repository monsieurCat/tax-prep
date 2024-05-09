package com.skillstorm.taxprep.server.dtos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FilingStatusDTOTest {

    @Test
    void testGettersAndSetters() {
        // Arrange
        FilingStatusDTO filingStatusDTO = new FilingStatusDTO();

        // Act
        filingStatusDTO.setStatus("Single");

        // Assert
        assertEquals("Single", filingStatusDTO.getStatus());
    }

    @Test
    void testDefaultConstructor() {
        // Arrange & Act
        FilingStatusDTO filingStatusDTO = new FilingStatusDTO();

        // Assert
        assertNull(filingStatusDTO.getStatus());
    }

    @Test
    void testSetStatusWithNullValue() {
        // Arrange
        FilingStatusDTO filingStatusDTO = new FilingStatusDTO();

        // Act
        filingStatusDTO.setStatus(null);

        // Assert
        assertNull(filingStatusDTO.getStatus());
    }
}
