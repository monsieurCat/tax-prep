package com.skillstorm.taxprep.server.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.skillstorm.taxprep.server.models.FilingStatus;
import com.skillstorm.taxprep.server.models.TaxBracket;

@DataJpaTest
public class TaxBracketRepositoryTest {

    @Autowired
    private TaxBracketRepository taxBracketRepository;

    @Autowired
    private FilingStatusRepository filingStatusRepository;

    @Test
    public void testFindByFilingStatusId_WithExistingData_ShouldReturnMatchingTaxBrackets() {
        FilingStatus filingStatus = new FilingStatus("Single");
        FilingStatus savedFilingStatus = filingStatusRepository.save(filingStatus);

        TaxBracket taxBracket1 = new TaxBracket(savedFilingStatus, BigDecimal.valueOf(0.1), 0, 50000);
        TaxBracket taxBracket2 = new TaxBracket(savedFilingStatus, BigDecimal.valueOf(0.2), 50001, 100000);
        taxBracketRepository.save(taxBracket1);
        taxBracketRepository.save(taxBracket2);

        List<TaxBracket> taxBrackets = taxBracketRepository.findByFilingStatus_Id(savedFilingStatus.getId());

        assertNotNull(taxBrackets);
        assertFalse(taxBrackets.isEmpty());
        assertEquals(2, taxBrackets.size());
    }

    @Test
    public void testFindByFilingStatusId_WithNonExistingData_ShouldReturnEmptyList() {
        int nonExistingFilingStatusId = 999;

        List<TaxBracket> taxBrackets = taxBracketRepository.findByFilingStatus_Id(nonExistingFilingStatusId);

        assertNotNull(taxBrackets);
        assertTrue(taxBrackets.isEmpty());
    }
}
