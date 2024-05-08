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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import com.skillstorm.taxprep.server.dtos.FilingStatusDTO;
import com.skillstorm.taxprep.server.dtos.TaxInfoDTO;
import com.skillstorm.taxprep.server.dtos.TaxResultsDTO;
import com.skillstorm.taxprep.server.exceptions.NotFoundException;
import com.skillstorm.taxprep.server.models.AppUser;
import com.skillstorm.taxprep.server.models.FilingStatus;
import com.skillstorm.taxprep.server.models.Income1099;
import com.skillstorm.taxprep.server.models.IncomeW2;
import com.skillstorm.taxprep.server.models.TaxInfo;
import com.skillstorm.taxprep.server.repositories.IncomeW2Repository;
import com.skillstorm.taxprep.server.services.FilingStatusService;
import com.skillstorm.taxprep.server.services.Income1099Service;
import com.skillstorm.taxprep.server.services.IncomeW2Service;
import com.skillstorm.taxprep.server.services.TaxInfoService;
import com.skillstorm.taxprep.server.services.UserService;
import com.skillstorm.taxprep.server.utilities.TaxCalculator;

@RestController
@RequestMapping("/tax_info")
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

  @GetMapping()
  public ResponseEntity<?> findTaxInfo(Principal principal) {
    try {
      int userId = userService.findUserIdByUsername(principal.getName());
      TaxInfo taxInfo = taxInfoService.findTaxInfoByUserId(userId);

      return new ResponseEntity<TaxInfo>(taxInfo, HttpStatus.OK);
    } catch (UsernameNotFoundException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
    }
  }

  @GetMapping("/full")
  public ResponseEntity<?> findFullTaxInfo(Principal principal) {
    try {
      int userId = userService.findUserIdByUsername(principal.getName());
      TaxInfo taxInfo = taxInfoService.findTaxInfoByUserId(userId);
      List<IncomeW2> incomesW2 = incomeW2Service.getIncomeByTaxInfoId(taxInfo.getId());
      List<Income1099> incomes1099 = income1099Service.getIncomeByTaxInfoId(taxInfo.getId());
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

  @GetMapping("/calculate")
  public ResponseEntity<?> calculateTax(Principal principal) {
    try {
      int userId = userService.findUserIdByUsername(principal.getName());
      TaxInfo taxInfo = taxInfoService.findTaxInfoByUserId(userId);
      List<IncomeW2> incomesW2 = incomeW2Service.getIncomeByTaxInfoId(taxInfo.getId());
      List<Income1099> incomes1099 = income1099Service.getIncomeByTaxInfoId(taxInfo.getId());

      TaxCalculator taxCalculator = new TaxCalculator();
      TaxResultsDTO results = taxCalculator.calculate(taxInfo, incomesW2, incomes1099);

      return new ResponseEntity<TaxResultsDTO>(results, HttpStatus.OK);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
    }
    
  }

  @GetMapping("/filing_status")
  public ResponseEntity<?> findFilingStatus(Principal principal) {
    int userId = userService.findUserIdByUsername(principal.getName());
    TaxInfo taxInfo = taxInfoService.findTaxInfoByUserId(userId);
    FilingStatus filingStatus = taxInfo.getFilingStatus();

    return new ResponseEntity<FilingStatus>(filingStatus, HttpStatus.OK);
  }

  @PutMapping("/filing_status")
  public ResponseEntity<?> updateFilingStatus(Principal principal, @RequestBody FilingStatusDTO filingStatusDTO) {
    try {
      int userId = userService.findUserIdByUsername(principal.getName());
      TaxInfo taxInfo = taxInfoService.findTaxInfoByUserId(userId);
      FilingStatus newFilingStatus = filingStatusService.getByStatus(filingStatusDTO.getStatus());
      taxInfo.setFilingStatus(newFilingStatus);
      taxInfoService.saveTaxInfo(taxInfo);

      return new ResponseEntity<FilingStatus>(newFilingStatus, HttpStatus.OK);
    } catch (NotFoundException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
    }
  }

  @GetMapping("/income_w2")
  public ResponseEntity<?> findW2Income(Principal principal) {
    try {
      int userId = userService.findUserIdByUsername(principal.getName());
      int taxInfoId = taxInfoService.findTaxInfoIdByUserId(userId);
      List<IncomeW2> incomes = incomeW2Service.getIncomeByTaxInfoId(taxInfoId);

      return new ResponseEntity<List<IncomeW2>>(incomes, HttpStatus.OK);
    } catch (UsernameNotFoundException | NotFoundException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
    }
  }

  @GetMapping("/income_1099")
  public ResponseEntity<?> find1099Income(Principal principal) {
    try {
      int userId = userService.findUserIdByUsername(principal.getName());
      int taxInfoId = taxInfoService.findTaxInfoIdByUserId(userId);
      List<Income1099> incomes = income1099Service.getIncomeByTaxInfoId(taxInfoId);

      return new ResponseEntity<List<Income1099>>(incomes, HttpStatus.OK);
    } catch (UsernameNotFoundException | NotFoundException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
    }
  }

  /* @PostMapping()
  public ResponseEntity<?> createTaxInfo(Principal principal, @RequestBody TaxInfo taxInfo) {
    TaxInfo createdTaxInfo = taxInfoService.saveTaxInfo(taxInfo);

    return new ResponseEntity<TaxInfo>(createdTaxInfo, HttpStatus.OK);
  } */

  @PostMapping("/full")
  public ResponseEntity<?> createFullTaxInfo(Principal principal, @RequestBody TaxInfoDTO taxInfoDTO) {
    try {
      int userId = userService.findUserIdByUsername(principal.getName());
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
      List<IncomeW2> savedIncomeW2 = taxInfoService.saveW2Income(taxInfoDTO.getIncomeW2());

      // Create a set of 1099 IDs from the incoming 1099 list for efficient lookup
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
      List<Income1099> savedIncome1099 = taxInfoService.save1099Income(taxInfoDTO.getIncome1099());

      // Update the TaxInfoDTO with all of the newly saved/updated records
      taxInfoDTO.setFilingStatus(filingStatus);
      taxInfoDTO.setIncomeW2(savedIncomeW2);
      taxInfoDTO.setIncome1099(savedIncome1099);

      return new ResponseEntity<TaxInfoDTO>(taxInfoDTO, HttpStatus.OK);

    } catch (NotFoundException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
    }
  }

  /* @PutMapping()
  public ResponseEntity<?> updateTaxInfo(@RequestBody TaxInfo taxInfo) {
    TaxInfo updatedTaxInfo = taxInfoService.saveTaxInfo(taxInfo);

    return new ResponseEntity<TaxInfo>(updatedTaxInfo, HttpStatus.OK);
  } */

  @DeleteMapping()
  public void deleteTaxInfo(@RequestBody TaxInfo taxInfo) {
    taxInfoService.deleteTaxInfo(taxInfo);
  }
}
