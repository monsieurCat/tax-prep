package com.skillstorm.taxprep.server.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.skillstorm.taxprep.server.models.FilingStatus;
import com.skillstorm.taxprep.server.models.TaxBracket;
import com.skillstorm.taxprep.server.repositories.TaxBracketRepository;

class TaxBracketServiceTest {

    @Mock
    private TaxBracketRepository taxBracketRepository;

    @InjectMocks
    private TaxBracketService taxBracketService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByFilingStatusID_ExistingId_ReturnsTaxBrackets() {
        // Arrange
        int filingStatusId = 1;
        FilingStatus filingStatus = new FilingStatus();
        List<TaxBracket> mockTaxBrackets = new ArrayList<>();
        mockTaxBrackets.add(new TaxBracket(1, filingStatus, BigDecimal.valueOf(0.1), 0, 10000));
        mockTaxBrackets.add(new TaxBracket(2, filingStatus, BigDecimal.valueOf(0.2), 10001, 20000));
        when(taxBracketRepository.findByFilingStatus_Id(filingStatusId)).thenReturn(mockTaxBrackets);

        // Act
        List<TaxBracket> result = taxBracketService.findByFilingStatusID(filingStatusId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(mockTaxBrackets, result);
    }

    @Test
    void testFindByFilingStatusID_NonExistingId_ReturnsEmptyList() {
        // Arrange
        int nonExistingId = 999;
        when(taxBracketRepository.findByFilingStatus_Id(nonExistingId)).thenReturn(new ArrayList<>());

        // Act
        List<TaxBracket> result = taxBracketService.findByFilingStatusID(nonExistingId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}