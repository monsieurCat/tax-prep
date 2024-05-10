package com.skillstorm.taxprep.server.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.skillstorm.taxprep.server.models.FilingStatus;
import com.skillstorm.taxprep.server.models.StandardizedDeduction;

@DataJpaTest
public class StandardizedDeductionRepositoryTest {

    @Autowired
    private StandardizedDeductionRepository standardizedDeductionRepository;

    @Autowired
    private FilingStatusRepository filingStatusRepository;

    @Test
    public void testSave_WithValidFilingStatus_ShouldSaveSuccessfully() {
      FilingStatus filingStatus = new FilingStatus();
      filingStatus.setStatus("Single");
      FilingStatus savedFilingStatus = filingStatusRepository.save(filingStatus);

      StandardizedDeduction standardizedDeduction = new StandardizedDeduction();
      standardizedDeduction.setFilingStatus(savedFilingStatus);
      standardizedDeduction.setDeductionAmount(1000);

      StandardizedDeduction savedStandardizedDeduction = standardizedDeductionRepository.save(standardizedDeduction);

      assertNotNull(savedStandardizedDeduction);
      assertNotNull(savedStandardizedDeduction.getId());
      assertEquals(1000, savedStandardizedDeduction.getDeductionAmount());

    }

    @Test
    public void testFindByFilingStatus_Id_WithNonExistingData_ShouldReturnEmptyOptional() {
        int nonExistingFilingStatusId = 999; // Assuming this ID does not exist in the database

        Optional<StandardizedDeduction> result = standardizedDeductionRepository.findByFilingStatus_Id(nonExistingFilingStatusId);

        assertFalse(result.isPresent());
    }

}
