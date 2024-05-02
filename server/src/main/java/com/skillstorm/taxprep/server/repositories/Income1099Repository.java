package com.skillstorm.taxprep.server.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.taxprep.server.models.Income1099;

@Repository
public interface Income1099Repository extends JpaRepository<Income1099, Integer> {
  
  public Optional<List<Income1099>> findByTaxInfoId(int taxInfoId);
}
