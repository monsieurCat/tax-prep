package com.skillstorm.taxprep.server.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.taxprep.server.models.Dependent;

@Repository
public interface DependentRepository extends JpaRepository<Dependent, Integer> {
  
  public Optional<List<Dependent>> findByTaxInfo_Id(int taxInfoId);
}
