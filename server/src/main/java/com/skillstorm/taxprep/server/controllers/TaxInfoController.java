package com.skillstorm.taxprep.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.taxprep.server.models.TaxInfo;
import com.skillstorm.taxprep.server.services.TaxInfoService;

@RestController
@RequestMapping("/tax_info")
public class TaxInfoController {
  
  @Autowired
  TaxInfoService taxInfoService;

  @GetMapping("/{userId}")
  public ResponseEntity<?> findTaxInfoByUserId(int userId) {
    TaxInfo taxInfo = taxInfoService.findTaxInfoByUserId(userId);

    return new ResponseEntity<TaxInfo>(taxInfo, HttpStatus.OK);
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
