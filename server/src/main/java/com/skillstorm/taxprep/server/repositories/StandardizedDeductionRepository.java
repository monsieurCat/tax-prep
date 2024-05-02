package com.skillstorm.taxprep.server.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.taxprep.server.models.StandardizedDeduction;

@Repository
public interface StandardizedDeductionRepository extends JpaRepository<StandardizedDeduction, Integer> {
  
  public Optional<List<StandardizedDeduction>> findByFilingStatus_Id(int filingStatusId);
}
