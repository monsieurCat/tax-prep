package com.skillstorm.taxprep.server.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FilingStatusTest {

    private FilingStatus filingStatus;

    @BeforeEach
    public void setUp() {
        filingStatus = new FilingStatus(1, "Single");
    }

    @Test
    public void testGetId() {
        assertEquals(1, filingStatus.getId());
    }

    @Test
    public void testGetStatus() {
        assertEquals("Single", filingStatus.getStatus());
    }

    @Test
    public void testEquals() {
        FilingStatus sameFilingStatus = new FilingStatus(1, "Single");
        FilingStatus differentFilingStatus = new FilingStatus(2, "Married");

        assertEquals(filingStatus, sameFilingStatus);
        assertNotEquals(filingStatus, differentFilingStatus);
    }

    @Test
    public void testHashCode() {
        FilingStatus sameFilingStatus = new FilingStatus(1, "Single");
        FilingStatus differentFilingStatus = new FilingStatus(2, "Married");

        assertEquals(filingStatus.hashCode(), sameFilingStatus.hashCode());
        assertNotEquals(filingStatus.hashCode(), differentFilingStatus.hashCode());
    }

    @Test
    public void testToString() {
        assertNotNull(filingStatus.toString());
    }
}