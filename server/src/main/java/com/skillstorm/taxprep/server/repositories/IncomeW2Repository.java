package com.skillstorm.taxprep.server.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.taxprep.server.models.IncomeW2;

@Repository
public interface IncomeW2Repository extends JpaRepository<IncomeW2, Integer> {
  
  public Optional<List<IncomeW2>> findByTaxInfoId(int taxInfoId);
}
