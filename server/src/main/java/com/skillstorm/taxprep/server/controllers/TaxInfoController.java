package com.skillstorm.taxprep.server.controllers;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.taxprep.server.dtos.FilingStatusDTO;
import com.skillstorm.taxprep.server.dtos.TaxInfoDTO;
import com.skillstorm.taxprep.server.dtos.TaxResultsDTO;
import com.skillstorm.taxprep.server.exceptions.NotFoundException;
import com.skillstorm.taxprep.server.models.FilingStatus;
import com.skillstorm.taxprep.server.models.Income1099;
import com.skillstorm.taxprep.server.models.IncomeW2;
import com.skillstorm.taxprep.server.models.TaxInfo;
import com.skillstorm.taxprep.server.services.FilingStatusService;
import com.skillstorm.taxprep.server.services.Income1099Service;
import com.skillstorm.taxprep.server.services.IncomeW2Service;
import com.skillstorm.taxprep.server.services.TaxInfoService;
import com.skillstorm.taxprep.server.services.UserService;
import com.skillstorm.taxprep.server.utilities.TaxCalculator;

@RestController
@RequestMapping("/tax_info")
@CrossOrigin("*")
public class TaxInfoController {
  
  @Autowired
  TaxInfoService taxInfoService;

  @Autowired
  UserService userService;

  @Autowired
  IncomeW2Service incomeW2Service;

  @Autowired
  Income1099Service income1099Service;

  @Autowired
  FilingStatusService filingStatusService;

  @Autowired
  TaxCalculator taxCalculator;

  // Mapping to find tax info of the currently authenticated user
  @GetMapping()
  public ResponseEntity<?> findTaxInfo(Principal principal) {
    try {

      // Get user's id
      int userId = userService.findUserIdByUsername(principal.getName());

      // Find the user's tax info by user id
      TaxInfo taxInfo = taxInfoService.findTaxInfoByUserId(userId);

      return new ResponseEntity<TaxInfo>(taxInfo, HttpStatus.OK);
    } catch (UsernameNotFoundException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
    }
  }

  // Get the user's financial details
  @GetMapping("/full")
  public ResponseEntity<?> findFullTaxInfo(Principal principal) {
    try {
      // Get the user's id
      int userId = userService.findUserIdByUsername(principal.getName());

      // Get the user's tax info by user id
      TaxInfo taxInfo = taxInfoService.findTaxInfoByUserId(userId);

      // Get the user's saved list of W2 incomes
      List<IncomeW2> incomesW2 = incomeW2Service.getIncomeByTaxInfoId(taxInfo.getId());

      // Get the user's saved list of 1099 incomes
      List<Income1099> incomes1099 = income1099Service.getIncomeByTaxInfoId(taxInfo.getId());

      // Construct the return values
      TaxInfoDTO taxInfoDTO = new TaxInfoDTO();
      taxInfoDTO.setFilingStatus(taxInfo.getFilingStatus());
      taxInfoDTO.setNumDependents(taxInfo.getNumDependents());
      taxInfoDTO.setIncomeW2(incomesW2);
      taxInfoDTO.setIncome1099(incomes1099);
      taxInfoDTO.setMortgageInterest(taxInfo.getMortgageInterest());
      taxInfoDTO.setDonations(taxInfo.getDonations());
      taxInfoDTO.setPropertyTax(taxInfo.getPropertyTax());
      taxInfoDTO.setMedical(taxInfo.getMedical());
      taxInfoDTO.setStudentLoanInterest(taxInfo.getStudentLoanInterest());
      taxInfoDTO.setOtherDeduction(taxInfo.getOtherDeduction());
      taxInfoDTO.setOtherIncome(taxInfo.getOtherIncome());

      return new ResponseEntity<TaxInfoDTO>(taxInfoDTO, HttpStatus.OK);
    } catch (UsernameNotFoundException | NotFoundException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
    }
  }

  // Mapping to get the calculations of a user's federal taxes
  @GetMapping("/calculate")
  public ResponseEntity<?> calculateTax(Principal principal) {
    
    try {

      // Get the user's id
      int userId = userService.findUserIdByUsername(principal.getName());

      // Get the user's tax info by user id
      TaxInfo taxInfo = taxInfoService.findTaxInfoByUserId(userId);

      // Get the user's saved list of W2 incomes
      List<IncomeW2> incomesW2 = incomeW2Service.getIncomeByTaxInfoId(taxInfo.getId());

      // Get the user's saved list of 1099 incomes
      List<Income1099> incomes1099 = income1099Service.getIncomeByTaxInfoId(taxInfo.getId());

      // Calculate the user's federal tax information
      TaxResultsDTO results = taxCalculator.calculate(taxInfo, incomesW2, incomes1099);

      return new ResponseEntity<TaxResultsDTO>(results, HttpStatus.OK);
    } catch (Exception e) {
      System.out.println("Error from calculator: " + e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
    }
  }

  // Mapping to get the user's saved filing status
  @GetMapping("/filing_status")
  public ResponseEntity<?> findFilingStatus(Principal principal) {
    // Get the user's id
    int userId = userService.findUserIdByUsername(principal.getName());

    // Get the user's tax information by user id
    TaxInfo taxInfo = taxInfoService.findTaxInfoByUserId(userId);

    // Get the user's filing status from the tax info
    FilingStatus filingStatus = taxInfo.getFilingStatus();

    return new ResponseEntity<FilingStatus>(filingStatus, HttpStatus.OK);
  }

  // Mapping to update the user's filing status
  @PutMapping("/filing_status")
  public ResponseEntity<?> updateFilingStatus(Principal principal, @RequestBody FilingStatusDTO filingStatusDTO) {
    try {

      // Get the user's id
      int userId = userService.findUserIdByUsername(principal.getName());

      // Get the user's tax info by user id
      TaxInfo taxInfo = taxInfoService.findTaxInfoByUserId(userId);

      // Set the user's filing status within tax info
      FilingStatus newFilingStatus = filingStatusService.getByStatus(filingStatusDTO.getStatus());
      taxInfo.setFilingStatus(newFilingStatus);

      // Save updated record in table
      taxInfoService.saveTaxInfo(taxInfo);

      return new ResponseEntity<FilingStatus>(newFilingStatus, HttpStatus.OK);
    } catch (NotFoundException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
    }
  }

  // Mapping to get the list of w2 incomes the user has saved
  @GetMapping("/income_w2")
  public ResponseEntity<?> findW2Income(Principal principal) {
    try {

      // Get the user's id
      int userId = userService.findUserIdByUsername(principal.getName());

      // Get the user's tax info by user id
      int taxInfoId = taxInfoService.findTaxInfoIdByUserId(userId);

      // Get the user's saved w2 incomes based on the tax info's id
      List<IncomeW2> incomes = incomeW2Service.getIncomeByTaxInfoId(taxInfoId);

      return new ResponseEntity<List<IncomeW2>>(incomes, HttpStatus.OK);
    } catch (UsernameNotFoundException | NotFoundException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
    }
  }

  // Mapping to get the list of 1099 incomes the user has saved
  @GetMapping("/income_1099")
  public ResponseEntity<?> find1099Income(Principal principal) {
    try {

      // Get the user's id
      int userId = userService.findUserIdByUsername(principal.getName());

      // Get the user's tax info by user id
      int taxInfoId = taxInfoService.findTaxInfoIdByUserId(userId);

      // Get the user's saved 1099 incomes based on the tax info's id
      List<Income1099> incomes = income1099Service.getIncomeByTaxInfoId(taxInfoId);

      return new ResponseEntity<List<Income1099>>(incomes, HttpStatus.OK);
    } catch (UsernameNotFoundException | NotFoundException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
    }
  }

  // Mapping to save/update the user's financial information
  @PostMapping("/full")
  public ResponseEntity<?> createFullTaxInfo(Principal principal, @RequestBody TaxInfoDTO taxInfoDTO) {
    try {
      // Get the user's id
      int userId = userService.findUserIdByUsername(principal.getName());

      // Get the user's tax info by user id
      TaxInfo taxInfo = taxInfoService.findTaxInfoByUserId(userId);

      // get existing income records to compare incoming income records to
      List<IncomeW2> existingIncomesW2 = incomeW2Service.getIncomeByTaxInfoId(taxInfo.getId());
      List<Income1099> existingIncomes1099 = income1099Service.getIncomeByTaxInfoId(taxInfo.getId());

      FilingStatus filingStatus = filingStatusService.getByStatus(taxInfoDTO.getFilingStatus().getStatus());

      // Update tax info record
      taxInfo.setFilingStatus(filingStatus);
      taxInfo.setNumDependents(taxInfoDTO.getNumDependents());
      taxInfo.setMortgageInterest(taxInfoDTO.getMortgageInterest());
      taxInfo.setDonations(taxInfoDTO.getDonations());
      taxInfo.setPropertyTax(taxInfoDTO.getPropertyTax());
      taxInfo.setMedical(taxInfoDTO.getMedical());
      taxInfo.setStudentLoanInterest(taxInfoDTO.getStudentLoanInterest());
      taxInfo.setOtherDeduction(taxInfoDTO.getOtherDeduction());
      taxInfo.setOtherIncome(taxInfoDTO.getOtherIncome());

      // Save tax info record
      taxInfoService.saveTaxInfo(taxInfo);

      // Create a set of W2 IDs from the incoming W2 list for efficient lookup
      if (taxInfoDTO.getIncomeW2() != null) {
        Set<Integer> incomingW2Ids = taxInfoDTO.getIncomeW2().stream()
                                                        .map(IncomeW2::getId)
                                                        .collect(Collectors.toSet());
      
        // Identify W2 records to delete
        List<IncomeW2> incomeW2ToDelete = existingIncomesW2.stream()
                                                          .filter(record -> !incomingW2Ids.contains(record.getId()))
                                                          .collect(Collectors.toList());

        // Delete identified W2 records
        for (IncomeW2 record : incomeW2ToDelete) {
          incomeW2Service.deleteIncomeById(record.getId());
        }

        // Now save the incoming W2 records
        // Incoming records that have ids indicate existing records, so those records will be updated
        // Incoming records that don't have ids indicate new records, so new records will be created in the table
        List<IncomeW2> savedIncomeW2 = taxInfoService.saveW2Income(taxInfoDTO.getIncomeW2(), taxInfo.getId());
        taxInfoDTO.setIncomeW2(savedIncomeW2);
      } else {
        for (IncomeW2 record : existingIncomesW2) {
          incomeW2Service.deleteIncomeById(record.getId());
        }
      }
      

      // Create a set of 1099 IDs from the incoming 1099 list for efficient lookup
      if (taxInfoDTO.getIncome1099() != null) {
        Set<Integer> incoming1099Ids = taxInfoDTO.getIncome1099().stream()
                                                        .map(Income1099::getId)
                                                        .collect(Collectors.toSet());
      
        // Identify 1099 records to delete
        List<Income1099> income1099ToDelete = existingIncomes1099.stream()
                                                          .filter(record -> !incoming1099Ids.contains(record.getId()))
                                                          .collect(Collectors.toList());

        // Delete identified 1099 records
        for (Income1099 record : income1099ToDelete) {
          income1099Service.deleteIncomeById(record.getId());
        }

        // Now save the incoming 1099 records
        // Incoming records that have ids indicate existing records, so those records will be updated
        // Incoming records that don't have ids indicate new records, so new records will be created in the table
        List<Income1099> savedIncome1099 = taxInfoService.save1099Income(taxInfoDTO.getIncome1099(), taxInfo.getId());
        taxInfoDTO.setIncome1099(savedIncome1099);
      } else {
        for (Income1099 record : existingIncomes1099) {
          incomeW2Service.deleteIncomeById(record.getId());
        }
      }
      

      // Update the TaxInfoDTO with all of the newly saved/updated records
      taxInfoDTO.setFilingStatus(filingStatus);

      return new ResponseEntity<TaxInfoDTO>(taxInfoDTO, HttpStatus.OK);

    } catch (NotFoundException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
    }
  }

  // Mapping to delete a user's tax info record
  @DeleteMapping()
  public void deleteTaxInfo(@RequestBody TaxInfo taxInfo) {
    taxInfoService.deleteTaxInfo(taxInfo);
  }
}
