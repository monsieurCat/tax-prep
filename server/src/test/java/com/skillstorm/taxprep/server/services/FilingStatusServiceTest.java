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
import com.skillstorm.taxprep.server.repositories.FilingStatusRepository;

class FilingStatusServiceTest {

    @Mock
    private FilingStatusRepository filingStatusRepository;

    @InjectMocks
    private FilingStatusService filingStatusService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetByStatus_ExistingStatus_ReturnsFilingStatus() {
        // Arrange
        String status = "Single";
        FilingStatus filingStatus = new FilingStatus(1, status);
        when(filingStatusRepository.findByStatus(status)).thenReturn(Optional.of(filingStatus));

        // Act
        FilingStatus result = filingStatusService.getByStatus(status);

        // Assert
        assertNotNull(result);
        assertEquals(filingStatus, result);
    }

    @Test
    void testGetByStatus_NonExistingStatus_ThrowsNotFoundException() {
        String status = "NonExistentStatus";
        when(filingStatusRepository.findByStatus(status)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> filingStatusService.getByStatus(status));
    }

    @Test
    void testGetByStatus_NullStatus_ThrowsIllegalArgumentException() {
        assertThrows(NotFoundException.class, () -> filingStatusService.getByStatus(null));
    }
}
