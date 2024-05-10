package com.skillstorm.taxprep.server.dtos;

import org.junit.jupiter.api.Test;

import com.skillstorm.taxprep.server.models.FilingStatus;
import com.skillstorm.taxprep.server.models.Income1099;
import com.skillstorm.taxprep.server.models.IncomeW2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TaxInfoDTOTest {

    @Test
    void testGettersAndSetters() {
        // Arrange
        FilingStatus filingStatus = new FilingStatus();
        filingStatus.setStatus("Single");

        List<IncomeW2> incomeW2List = new ArrayList<>();
        IncomeW2 incomeW2 = new IncomeW2();
        incomeW2.setIncome(new BigDecimal("50000.00"));
        incomeW2List.add(incomeW2);

        List<Income1099> income1099List = new ArrayList<>();
        Income1099 income1099 = new Income1099();
        income1099.setIncome(new BigDecimal("10000.00"));
        income1099List.add(income1099);

        TaxInfoDTO taxInfoDTO = new TaxInfoDTO();

        // Act
        taxInfoDTO.setFilingStatus(filingStatus);
        taxInfoDTO.setNumDependents(2);
        taxInfoDTO.setIncomeW2(incomeW2List);
        taxInfoDTO.setIncome1099(income1099List);
        taxInfoDTO.setMortgageInterest(new BigDecimal("2000.00"));
        taxInfoDTO.setDonations(new BigDecimal("500.00"));
        taxInfoDTO.setPropertyTax(new BigDecimal("1000.00"));
        taxInfoDTO.setMedical(new BigDecimal("300.00"));
        taxInfoDTO.setStudentLoanInterest(new BigDecimal("100.00"));
        taxInfoDTO.setOtherDeduction(new BigDecimal("50.00"));
        taxInfoDTO.setOtherIncome(new BigDecimal("200.00"));

        // Assert
        assertEquals(filingStatus, taxInfoDTO.getFilingStatus());
        assertEquals(2, taxInfoDTO.getNumDependents());
        assertEquals(incomeW2List, taxInfoDTO.getIncomeW2());
        assertEquals(income1099List, taxInfoDTO.getIncome1099());
        assertEquals(new BigDecimal("2000.00"), taxInfoDTO.getMortgageInterest());
        assertEquals(new BigDecimal("500.00"), taxInfoDTO.getDonations());
        assertEquals(new BigDecimal("1000.00"), taxInfoDTO.getPropertyTax());
        assertEquals(new BigDecimal("300.00"), taxInfoDTO.getMedical());
        assertEquals(new BigDecimal("100.00"), taxInfoDTO.getStudentLoanInterest());
        assertEquals(new BigDecimal("50.00"), taxInfoDTO.getOtherDeduction());
        assertEquals(new BigDecimal("200.00"), taxInfoDTO.getOtherIncome());
    }

    @Test
    void testDefaultValues() {
        TaxInfoDTO taxInfoDTO = new TaxInfoDTO();

        assertNull(taxInfoDTO.getFilingStatus());
        assertEquals(0, taxInfoDTO.getNumDependents());
        assertNull(taxInfoDTO.getIncomeW2());
        assertNull(taxInfoDTO.getIncome1099());
        assertEquals(BigDecimal.ZERO, taxInfoDTO.getMortgageInterest());
        assertEquals(BigDecimal.ZERO, taxInfoDTO.getDonations());
        assertEquals(BigDecimal.ZERO, taxInfoDTO.getPropertyTax());
        assertEquals(BigDecimal.ZERO, taxInfoDTO.getMedical());
        assertEquals(BigDecimal.ZERO, taxInfoDTO.getStudentLoanInterest());
        assertEquals(BigDecimal.ZERO, taxInfoDTO.getOtherDeduction());
        assertEquals(BigDecimal.ZERO, taxInfoDTO.getOtherIncome());
    }
}