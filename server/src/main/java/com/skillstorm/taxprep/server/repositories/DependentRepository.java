package com.skillstorm.taxprep.server.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillstorm.taxprep.server.models.Dependent;

public interface DependentRepository extends JpaRepository<Dependent, Integer> {
  
  public Optional<List<Dependent>> findByTax_Info_Id(int taxInfoId);
}
