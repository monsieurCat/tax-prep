package com.skillstorm.taxprep.server.controllers;

import java.util.List;

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

import com.skillstorm.taxprep.server.models.Dependent;
import com.skillstorm.taxprep.server.services.DependentService;

@RestController
@RequestMapping("/dependents")
public class DependentController {

  @Autowired
  DependentService dependentService;

  @GetMapping("/{taxInfoId}")
  public ResponseEntity<List<Dependent>> findDependentsByTaxInfoId(int taxInfoId) {
    List<Dependent> dependents = dependentService.findDependentsByTaxInfoId(taxInfoId);

    return new ResponseEntity<List<Dependent>>(dependents, HttpStatus.OK);
  }

  @GetMapping("/{dependentId}")
  public ResponseEntity<Dependent> findDependentById(int dependentId) {
    Dependent dependent = dependentService.findDependentById(dependentId);

    return new ResponseEntity<Dependent>(dependent, HttpStatus.OK);
  }

  @PostMapping("/dependent")
  public ResponseEntity<Dependent> saveDependent(@RequestBody Dependent dependent) {
    Dependent savedDependent = dependentService.saveDependent(dependent);

    return new ResponseEntity<Dependent>(savedDependent, HttpStatus.OK);
  }

  @PutMapping("/dependent")
  public ResponseEntity<Dependent> updateDependent(@RequestBody Dependent dependent) {
    Dependent updatedDependent = dependentService.saveDependent(dependent);

    return new ResponseEntity<Dependent>(updatedDependent, HttpStatus.OK);
  }

  @DeleteMapping("/dependent")
  public ResponseEntity<?> deleteDependent(@RequestBody Dependent dependent) {
    dependentService.deleteDependent(dependent);
    return ResponseEntity.noContent().build();
  }
  
}
