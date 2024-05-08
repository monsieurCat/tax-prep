package com.skillstorm.taxprep.server.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.taxprep.server.exceptions.NotFoundException;
import com.skillstorm.taxprep.server.models.StandardizedDeduction;
import com.skillstorm.taxprep.server.repositories.StandardizedDeductionRepository;

@Service
public class StandardizedDeductionService {
  
  @Autowired
  private StandardizedDeductionRepository standardizedDeductionRepository;

  public StandardizedDeduction getByFilingStatusId(int filingStatusId) {
    Optional<StandardizedDeduction> standardizedDeduction = standardizedDeductionRepository.findByFilingStatus_Id(filingStatusId);

    if (standardizedDeduction.isPresent()) {
      return standardizedDeduction.get();
    } else {
      throw new NotFoundException("Standardized deduction not found for filing status id: " + filingStatusId);
    }
  }
}