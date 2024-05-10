package com.skillstorm.taxprep.server.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.skillstorm.taxprep.server.exceptions.NotFoundException;
import com.skillstorm.taxprep.server.models.FilingStatus;
import com.skillstorm.taxprep.server.models.StandardizedDeduction;
import com.skillstorm.taxprep.server.repositories.StandardizedDeductionRepository;

class StandardizedDeductionServiceTest {

    @Mock
    private StandardizedDeductionRepository standardizedDeductionRepository;

    @InjectMocks
    private StandardizedDeductionService standardizedDeductionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetByFilingStatusId_ExistingId_ReturnsStandardizedDeduction() {
        int filingStatusId = 1;
        FilingStatus filingStatus = new FilingStatus();
        StandardizedDeduction mockStandardizedDeduction = new StandardizedDeduction(1, filingStatus, 100);
        when(standardizedDeductionRepository.findByFilingStatus_Id(filingStatusId))
                .thenReturn(Optional.of(mockStandardizedDeduction));

        StandardizedDeduction result = standardizedDeductionService.getByFilingStatusId(filingStatusId);

        assertNotNull(result);
        assertEquals(mockStandardizedDeduction, result);
    }

    @Test
    void testGetByFilingStatusId_NonExistingId_ThrowsNotFoundException() {
        int nonExistingId = 999;
        when(standardizedDeductionRepository.findByFilingStatus_Id(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> standardizedDeductionService.getByFilingStatusId(nonExistingId));
    }
}
