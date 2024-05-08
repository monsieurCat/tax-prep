package com.skillstorm.taxprep.server.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class TaxBracketTest {

    @Test
    public void testGettersAndSetters() {
        // Create a FilingStatus instance for testing
        FilingStatus filingStatus = new FilingStatus();

        // Create a TaxBracket instance
        TaxBracket bracket = new TaxBracket();
        bracket.setId(1);
        bracket.setFilingStatus(filingStatus);
        bracket.setRate(BigDecimal.valueOf(0.25));
        bracket.setMinIncome(10000);
        bracket.setMaxIncome(20000);

        // Test getters
        assertEquals(1, bracket.getId());
        assertEquals(filingStatus, bracket.getFilingStatus());
        assertEquals(BigDecimal.valueOf(0.25), bracket.getRate());
        assertEquals(10000, bracket.getMinIncome());
        assertEquals(20000, bracket.getMaxIncome());

        // Test setters
        FilingStatus newFilingStatus = new FilingStatus();
        bracket.setFilingStatus(newFilingStatus);
        assertEquals(newFilingStatus, bracket.getFilingStatus());

        bracket.setRate(BigDecimal.valueOf(0.30));
        assertEquals(BigDecimal.valueOf(0.30), bracket.getRate());

        bracket.setMinIncome(15000);
        assertEquals(15000, bracket.getMinIncome());

        bracket.setMaxIncome(30000);
        assertEquals(30000, bracket.getMaxIncome());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Create two instances with the same properties
        FilingStatus filingStatus1 = new FilingStatus();
        TaxBracket bracket1 = new TaxBracket(1, filingStatus1, BigDecimal.valueOf(0.25), 10000, 20000);

        FilingStatus filingStatus2 = new FilingStatus();
        TaxBracket bracket2 = new TaxBracket(1, filingStatus2, BigDecimal.valueOf(0.25), 10000, 20000);

        // Test equals and hashCode
        assertEquals(bracket1, bracket2);
        assertEquals(bracket1.hashCode(), bracket2.hashCode());
    }

    @Test
    public void testToString() {
        FilingStatus filingStatus = new FilingStatus();
        TaxBracket bracket = new TaxBracket(1, filingStatus, BigDecimal.valueOf(0.25), 10000, 20000);

        String expected = "TaxBracket [id=1, filingStatus=" + filingStatus + ", rate=0.25, minIncome=10000, maxIncome=20000]";
        assertEquals(expected, bracket.toString());
    }
}
