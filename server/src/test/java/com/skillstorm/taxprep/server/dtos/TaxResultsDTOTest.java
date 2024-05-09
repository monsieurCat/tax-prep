package com.skillstorm.taxprep.server.dtos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaxResultsDTOTest {

    @Test
    void testGettersAndSetters() {
        // Arrange
        TaxResultsDTO taxResultsDTO = new TaxResultsDTO();

        // Act
        taxResultsDTO.setIncomeW2(50000.0);
        taxResultsDTO.setIncome1099(10000.0);
        taxResultsDTO.setTotalIncome(60000.0);
        taxResultsDTO.setDeductions(10000.0);
        taxResultsDTO.setTaxableIncome(50000.0);
        taxResultsDTO.setMarginalTaxRate(25.0);
        taxResultsDTO.setEffectiveTaxRate(20.0);
        taxResultsDTO.setTaxWithheld(15000.0);
        taxResultsDTO.setChildTaxCredit(2000.0);
        taxResultsDTO.setEarnedIncomeTaxCredit(1000.0);
        taxResultsDTO.setTotalTaxAmount(10000.0);
        taxResultsDTO.setFinalTaxAmount(9000.0);

        // Assert
        assertEquals(50000.0, taxResultsDTO.getIncomeW2());
        assertEquals(10000.0, taxResultsDTO.getIncome1099());
        assertEquals(60000.0, taxResultsDTO.getTotalIncome());
        assertEquals(10000.0, taxResultsDTO.getDeductions());
        assertEquals(50000.0, taxResultsDTO.getTaxableIncome());
        assertEquals(25.0, taxResultsDTO.getMarginalTaxRate());
        assertEquals(20.0, taxResultsDTO.getEffectiveTaxRate());
        assertEquals(15000.0, taxResultsDTO.getTaxWithheld());
        assertEquals(2000.0, taxResultsDTO.getChildTaxCredit());
        assertEquals(1000.0, taxResultsDTO.getEarnedIncomeTaxCredit());
        assertEquals(10000.0, taxResultsDTO.getTotalTaxAmount());
        assertEquals(9000.0, taxResultsDTO.getFinalTaxAmount());
    }
}
