package com.skillstorm.taxprep.server.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

public class StandardizedDeductionTest {

    @Test
    public void testGettersAndSetters() {
        // Create a FilingStatus instance for testing
        FilingStatus filingStatus = new FilingStatus();

        // Create a StandardizedDeduction instance
        StandardizedDeduction deduction = new StandardizedDeduction();
        deduction.setId(1);
        deduction.setFilingStatus(filingStatus);
        deduction.setDeductionAmount(5000);

        // Test getters
        assertEquals(1, deduction.getId());
        assertEquals(filingStatus, deduction.getFilingStatus());
        assertEquals(5000, deduction.getDeductionAmount());

        // Test setters
        FilingStatus newFilingStatus = new FilingStatus();
        deduction.setFilingStatus(newFilingStatus);
        assertEquals(newFilingStatus, deduction.getFilingStatus());

        deduction.setDeductionAmount(6000);
        assertEquals(6000, deduction.getDeductionAmount());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Create two instances with the same properties
        FilingStatus filingStatus1 = new FilingStatus();
        StandardizedDeduction deduction1 = new StandardizedDeduction(1, filingStatus1, 5000);

        FilingStatus filingStatus2 = new FilingStatus();
        StandardizedDeduction deduction2 = new StandardizedDeduction(1, filingStatus2, 5000);

        // Test equals and hashCode
        assertEquals(deduction1, deduction2);
        assertEquals(deduction1.hashCode(), deduction2.hashCode());
    }

    @Test
    public void testToString() {
        FilingStatus filingStatus = new FilingStatus();
        StandardizedDeduction deduction = new StandardizedDeduction(1, filingStatus, 5000);

        String expected = "StandardizedDeduction [id=1, filingStatus=" + filingStatus + ", deductionAmount=5000]";
        assertEquals(expected, deduction.toString());
    }
}
