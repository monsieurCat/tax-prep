package com.skillstorm.taxprep.server.controllers;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.taxprep.server.exceptions.NotFoundException;
import com.skillstorm.taxprep.server.models.AppUser;
import com.skillstorm.taxprep.server.models.FilingStatus;
import com.skillstorm.taxprep.server.models.Income1099;
import com.skillstorm.taxprep.server.models.IncomeW2;
import com.skillstorm.taxprep.server.models.TaxInfo;
import com.skillstorm.taxprep.server.services.Income1099Service;
import com.skillstorm.taxprep.server.services.IncomeW2Service;
import com.skillstorm.taxprep.server.services.TaxInfoService;
import com.skillstorm.taxprep.server.services.UserService;

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

  @GetMapping()
  public ResponseEntity<?> findTaxInfoByUserId(Principal principal) {
    AppUser user = userService.loadUserByUsername(principal.getName());
    TaxInfo taxInfo = taxInfoService.findTaxInfoByUserId(user.getId());

    return new ResponseEntity<TaxInfo>(taxInfo, HttpStatus.OK);
  }

  @GetMapping("/filing_status")
  public ResponseEntity<?> findFilingStatus(Principal principal) {
    AppUser user = userService.loadUserByUsername(principal.getName());
    TaxInfo taxInfo = taxInfoService.findTaxInfoByUserId(user.getId());
    FilingStatus filingStatus = taxInfo.getFilingStatus();

    return new ResponseEntity<FilingStatus>(filingStatus, HttpStatus.OK);
  }

  @GetMapping("/filing_status/{taxInfoId}")
  public ResponseEntity<?> findFilingStatusByTaxInfoId(@PathVariable int taxInfoId) {
    TaxInfo taxInfo = taxInfoService.findByTaxInfoId(taxInfoId);
    FilingStatus filingStatus = taxInfo.getFilingStatus();

    return new ResponseEntity<FilingStatus>(filingStatus, HttpStatus.OK);
  }

  @GetMapping("/filing_status/{userId}")
  public ResponseEntity<?> findFilingStatusByUserId(@PathVariable int userId) {
    TaxInfo taxInfo = taxInfoService.findTaxInfoByUserId(userId);
    FilingStatus filingStatus = taxInfo.getFilingStatus();

    return new ResponseEntity<FilingStatus>(filingStatus, HttpStatus.OK);
  }

  @GetMapping("/income_w2")
  public ResponseEntity<?> findW2Income(Principal principal) {
    try {
      AppUser user = userService.loadUserByUsername(principal.getName());
      int taxInfoId = taxInfoService.findTaxInfoIdByUserId(user.getId());
      List<IncomeW2> incomes = incomeW2Service.getIncomeByTaxInfoId(taxInfoId);

      return new ResponseEntity<List<IncomeW2>>(incomes, HttpStatus.OK);
    } catch (NotFoundException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
    }
  }

  @GetMapping("/income_w2/{taxInfoId}")
  public ResponseEntity<?> findW2IncomeByTaxInfoId(@PathVariable int taxInfoId) {
    List<IncomeW2> incomes = taxInfoService.findW2IncomeByTaxInfoId(taxInfoId);

    return new ResponseEntity<List<IncomeW2>>(incomes, HttpStatus.OK);
  }

  @GetMapping("/income_1099")
  public ResponseEntity<?> find1099Income(Principal principal) {
    AppUser user = userService.loadUserByUsername(principal.getName());
    int taxInfoId = taxInfoService.findTaxInfoIdByUserId(user.getId());
    List<IncomeW2> incomes = incomeW2Service.getIncomeByTaxInfoId(taxInfoId);

    return new ResponseEntity<List<IncomeW2>>(incomes, HttpStatus.OK);
  }

  @GetMapping("/income_1099/{taxInfoId}")
  public ResponseEntity<?> find1099IncomeByTaxInfoId(@PathVariable int taxInfoId) {
    List<Income1099> incomes = taxInfoService.find1099IncomeByTaxInfoId(taxInfoId);

    return new ResponseEntity<List<Income1099>>(incomes, HttpStatus.OK);
  }

  @PostMapping()
  public ResponseEntity<?> createTaxInfo(@RequestBody TaxInfo taxInfo) {
    TaxInfo createdTaxInfo = taxInfoService.saveTaxInfo(taxInfo);

    return new ResponseEntity<TaxInfo>(createdTaxInfo, HttpStatus.OK);
  }

  @PutMapping()
  public ResponseEntity<?> updateTaxInfo(@RequestBody TaxInfo taxInfo) {
    TaxInfo updatedTaxInfo = taxInfoService.saveTaxInfo(taxInfo);

    return new ResponseEntity<TaxInfo>(updatedTaxInfo, HttpStatus.OK);
  }

  @DeleteMapping()
  public void deleteTaxInfo(@RequestBody TaxInfo taxInfo) {
    taxInfoService.deleteTaxInfo(taxInfo);
  }

}
