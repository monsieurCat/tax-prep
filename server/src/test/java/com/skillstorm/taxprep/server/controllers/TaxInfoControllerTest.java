package com.skillstorm.taxprep.server.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.skillstorm.taxprep.server.dtos.TaxInfoDTO;
import com.skillstorm.taxprep.server.models.FilingStatus;
import com.skillstorm.taxprep.server.models.Income1099;
import com.skillstorm.taxprep.server.models.IncomeW2;
import com.skillstorm.taxprep.server.models.TaxInfo;
import com.skillstorm.taxprep.server.services.FilingStatusService;
import com.skillstorm.taxprep.server.services.Income1099Service;
import com.skillstorm.taxprep.server.services.IncomeW2Service;
import com.skillstorm.taxprep.server.services.TaxInfoService;
import com.skillstorm.taxprep.server.services.UserService;

@ExtendWith(MockitoExtension.class)
public class TaxInfoControllerTest {

    @Mock
    private TaxInfoService taxInfoService;

    @Mock
    private UserService userService;

    @Mock
    private IncomeW2Service incomeW2Service;

    @Mock
    private Income1099Service income1099Service;

    @Mock
    private FilingStatusService filingStatusService;

    @InjectMocks
    private TaxInfoController taxInfoController;

    @Mock
    private Principal principal;

    @BeforeEach
    public void setupPrincipal() {
      principal = mock(Principal.class);
      when(principal.getName()).thenReturn("username");
    }

    @Test
    public void testFindTaxInfo() {
        when(userService.findUserIdByUsername(anyString())).thenReturn(1);
        when(taxInfoService.findTaxInfoByUserId(anyInt())).thenReturn(new TaxInfo());

        ResponseEntity<?> responseEntity = taxInfoController.findTaxInfo(principal);

        assert(responseEntity.getStatusCode()).equals(HttpStatus.OK);
    }

    @Test
    public void testFindFullTaxInfo() {
      TaxInfo taxInfo = new TaxInfo();
      taxInfo.setId(1);
      when(userService.findUserIdByUsername(anyString())).thenReturn(1);
      when(taxInfoService.findTaxInfoByUserId(anyInt())).thenReturn(taxInfo);

      ResponseEntity<?> responseEntity = taxInfoController.findFullTaxInfo(principal);

      assert(responseEntity.getStatusCode()).equals(HttpStatus.OK);
    }

    @Test
    public void testFindFilingStatus() {
        TaxInfo taxInfo = new TaxInfo();
        FilingStatus filingStatus = new FilingStatus();
        taxInfo.setFilingStatus(filingStatus);
        when(userService.findUserIdByUsername(anyString())).thenReturn(1);
        when(taxInfoService.findTaxInfoByUserId(anyInt())).thenReturn(taxInfo);

        ResponseEntity<?> responseEntity = taxInfoController.findFilingStatus(principal);

        assert(responseEntity.getStatusCode()).equals(HttpStatus.OK);
    }

    @Test
    public void testFindW2Income() {
        when(userService.findUserIdByUsername(anyString())).thenReturn(1);
        when(taxInfoService.findTaxInfoIdByUserId(anyInt())).thenReturn(1);
        when(incomeW2Service.getIncomeByTaxInfoId(anyInt())).thenReturn(Collections.singletonList(new IncomeW2()));

        ResponseEntity<?> responseEntity = taxInfoController.findW2Income(principal);

        assert(responseEntity.getStatusCode()).equals(HttpStatus.OK);
    }

    @Test
    public void testFind1099Income() {
        when(userService.findUserIdByUsername(anyString())).thenReturn(1);
        when(taxInfoService.findTaxInfoIdByUserId(anyInt())).thenReturn(1);
        when(income1099Service.getIncomeByTaxInfoId(anyInt())).thenReturn(Collections.singletonList(new Income1099()));

        ResponseEntity<?> responseEntity = taxInfoController.find1099Income(principal);

        assert(responseEntity.getStatusCode()).equals(HttpStatus.OK);
    }

    @Test
    public void testCreateFullTaxInfo() {

      // Prepare test data
      TaxInfoDTO taxInfoDTO = new TaxInfoDTO();
      taxInfoDTO.setFilingStatus(new FilingStatus("Single"));
      taxInfoDTO.setNumDependents(2);
      taxInfoDTO.setMortgageInterest(BigDecimal.valueOf(1500));
      taxInfoDTO.setDonations(BigDecimal.valueOf(500));
      taxInfoDTO.setPropertyTax(BigDecimal.valueOf(2000));
      taxInfoDTO.setMedical(BigDecimal.valueOf(1000));
      taxInfoDTO.setStudentLoanInterest(BigDecimal.valueOf(300));
      taxInfoDTO.setOtherDeduction(BigDecimal.valueOf(200));
      taxInfoDTO.setOtherIncome(BigDecimal.valueOf(100));
      
      List<IncomeW2> incomeW2List = new ArrayList<>();
      IncomeW2 incomeW21 = new IncomeW2();
      incomeW21.setId(1);
      incomeW21.setIncome(BigDecimal.valueOf(50000));
      incomeW21.setEmployerEin("123456789");
      incomeW21.setEmployerStreet1("Street 1");
      incomeW21.setEmployerStreet2("Street 2");
      incomeW21.setEmployerCity("City");
      incomeW21.setEmployerState("State");
      incomeW21.setEmployerZipcode("12345");
      incomeW2List.add(incomeW21);
      IncomeW2 incomeW22 = new IncomeW2();
      incomeW22.setIncome(BigDecimal.valueOf(60000));
      incomeW22.setEmployerEin("987654321");
      incomeW22.setEmployerStreet1("Street 3");
      incomeW22.setEmployerStreet2("Street 4");
      incomeW22.setEmployerCity("City");
      incomeW22.setEmployerState("State");
      incomeW22.setEmployerZipcode("54321");
      incomeW2List.add(incomeW22);
      taxInfoDTO.setIncomeW2(incomeW2List);
      
      List<Income1099> income1099List = new ArrayList<>();
      Income1099 income10991 = new Income1099.Builder()
                              .taxInfoId(1)
                              .income(BigDecimal.valueOf(1000))
                              .withholdings(BigDecimal.valueOf(200))
                              .employerEin("123456789")
                              .employerStreet1("Street 1")
                              .employerStreet2("Street 2")
                              .employerCity("City")
                              .employerState("State")
                              .employerZipcode("12345")
                              .build();
      income1099List.add(income10991);
      Income1099 income10992 = new Income1099.Builder()
                              .taxInfoId(1)
                              .income(BigDecimal.valueOf(2000))
                              .withholdings(BigDecimal.valueOf(300))
                              .employerEin("987654321")
                              .employerStreet1("Street 3")
                              .employerStreet2("Street 4")
                              .employerCity("City")
                              .employerState("State")
                              .employerZipcode("54321")
                              .build();
      income1099List.add(income10992);
      taxInfoDTO.setIncome1099(income1099List);

      // Mock userService to return user ID
      when(userService.findUserIdByUsername("username")).thenReturn(1);

      // Mock taxInfoService to return a TaxInfo object
      TaxInfo taxInfo = new TaxInfo();
      taxInfo.setId(1);
      when(taxInfoService.findTaxInfoByUserId(1)).thenReturn(taxInfo);

      // Mock incomeW2Service to return a list of IncomeW2 objects
      when(incomeW2Service.getIncomeByTaxInfoId(1)).thenReturn(incomeW2List);

      // Mock income1099Service to return a list of Income1099 objects
      when(income1099Service.getIncomeByTaxInfoId(1)).thenReturn(income1099List);

      // Mock filingStatusService to return a FilingStatus object
      FilingStatus filingStatus = new FilingStatus("Single");
      when(filingStatusService.getByStatus("Single")).thenReturn(filingStatus);

      // Mock taxInfoService to save TaxInfo object and return the saved object
      when(taxInfoService.saveTaxInfo(any(TaxInfo.class))).thenReturn(taxInfo);

      // Call the controller method
      ResponseEntity<?> responseEntity = taxInfoController.createFullTaxInfo(principal, taxInfoDTO);

      // Verify that the response status code is HttpStatus.OK
      assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

      // Verify interactions with services
      verify(userService).findUserIdByUsername("username");
      verify(taxInfoService).findTaxInfoByUserId(1);
      verify(incomeW2Service).getIncomeByTaxInfoId(1);
      verify(income1099Service).getIncomeByTaxInfoId(1);
      verify(filingStatusService).getByStatus("Single");
      verify(taxInfoService).saveTaxInfo(any(TaxInfo.class));
    }
}