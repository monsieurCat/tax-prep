package com.skillstorm.taxprep.server.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.skillstorm.taxprep.server.models.FilingStatus;

@DataJpaTest
public class FilingStatusRepositoryTest {

    @Autowired
    private FilingStatusRepository filingStatusRepository;

    private FilingStatus singleStatus;
    private FilingStatus marriedStatus;

    @BeforeEach
    public void setup() {
        singleStatus = new FilingStatus();
        singleStatus.setStatus("Single");
        filingStatusRepository.save(singleStatus);

        marriedStatus = new FilingStatus();
        marriedStatus.setStatus("Married");
        filingStatusRepository.save(marriedStatus);
    }

    @Test
    public void testFindByStatus() {
        String status = "Single";

        Optional<FilingStatus> result = filingStatusRepository.findByStatus(status);

        // Then
        assertTrue(result.isPresent());
        assertEquals(status, result.get().getStatus());
    }

    @Test
    public void testFindByStatus_NotFound() {
        String status = "Head of Household";

        Optional<FilingStatus> result = filingStatusRepository.findByStatus(status);

        assertFalse(result.isPresent());
    }

    @Test
    public void testSave() {
        FilingStatus newStatus = new FilingStatus();
        newStatus.setStatus("Head of Household");

        FilingStatus savedStatus = filingStatusRepository.save(newStatus);

        assertTrue(savedStatus.getId() > 0);
        assertEquals(newStatus.getStatus(), savedStatus.getStatus());
    }

    @Test
    public void testSave_WithExistingStatus() {
      FilingStatus existingStatus = new FilingStatus();
      existingStatus.setStatus("Single");
      existingStatus.setId(singleStatus.getId()); 

      FilingStatus savedStatus = filingStatusRepository.save(existingStatus);

      assertEquals(singleStatus.getId(), savedStatus.getId());
      assertEquals(existingStatus.getStatus(), savedStatus.getStatus());
    }
}
