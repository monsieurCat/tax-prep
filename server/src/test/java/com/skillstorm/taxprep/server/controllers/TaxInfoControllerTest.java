package com.skillstorm.taxprep.server.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.skillstorm.taxprep.server.models.TaxInfo;
import com.skillstorm.taxprep.server.services.TaxInfoService;

public class TaxInfoControllerTest {

    @Mock
    private TaxInfoService taxInfoService;

    @InjectMocks
    private TaxInfoController taxInfoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindTaxInfoByUserId() {
        // Mock data
        int userId = 1;
        TaxInfo mockTaxInfo = new TaxInfo();
        mockTaxInfo.setId(1);
        when(taxInfoService.findTaxInfoByUserId(userId)).thenReturn(mockTaxInfo);

        // Invoke controller method
        ResponseEntity<?> responseEntity = taxInfoController.findTaxInfoByUserId(userId);

        // Verify service method invocation
        verify(taxInfoService).findTaxInfoByUserId(userId);

        // Assert response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockTaxInfo, responseEntity.getBody());
    }

    @Test
    public void testCreateTaxInfo() {
        // Mock data
        TaxInfo taxInfo = new TaxInfo();
        TaxInfo createdTaxInfo = new TaxInfo();
        when(taxInfoService.saveTaxInfo(taxInfo)).thenReturn(createdTaxInfo);

        // Invoke controller method
        ResponseEntity<?> responseEntity = taxInfoController.createTaxInfo(taxInfo);

        // Verify service method invocation
        verify(taxInfoService).saveTaxInfo(taxInfo);

        // Assert response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(createdTaxInfo, responseEntity.getBody());
    }

    @Test
    public void testUpdateTaxInfo() {
        // Mock data
        TaxInfo taxInfo = new TaxInfo();
        TaxInfo updatedTaxInfo = new TaxInfo();
        when(taxInfoService.saveTaxInfo(taxInfo)).thenReturn(updatedTaxInfo);

        // Invoke controller method
        ResponseEntity<?> responseEntity = taxInfoController.updateTaxInfo(taxInfo);

        // Verify service method invocation
        verify(taxInfoService).saveTaxInfo(taxInfo);

        // Assert response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedTaxInfo, responseEntity.getBody());
    }

    @Test
    public void testDeleteTaxInfo() {
        // Mock data
        TaxInfo taxInfo = new TaxInfo();

        // Invoke controller method
        taxInfoController.deleteTaxInfo(taxInfo);

        // Verify service method invocation
        verify(taxInfoService).deleteTaxInfo(taxInfo);

        // Assert response
        //ResponseEntity<?> responseEntity = taxInfoController.deleteTaxInfo(taxInfo);
        //assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}