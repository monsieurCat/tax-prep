package com.skillstorm.taxprep.server.dtos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FilingStatusDTOTest {

    @Test
    void testGettersAndSetters() {
        FilingStatusDTO filingStatusDTO = new FilingStatusDTO();

        filingStatusDTO.setStatus("Single");

        assertEquals("Single", filingStatusDTO.getStatus());
    }

    @Test
    void testDefaultConstructor() {
        FilingStatusDTO filingStatusDTO = new FilingStatusDTO();

        assertNull(filingStatusDTO.getStatus());
    }

    @Test
    void testSetStatusWithNullValue() {
        FilingStatusDTO filingStatusDTO = new FilingStatusDTO();

        filingStatusDTO.setStatus(null);

        assertNull(filingStatusDTO.getStatus());
    }
}
