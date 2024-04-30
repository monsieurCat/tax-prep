package com.skillstorm.taxprep.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.taxprep.server.models.Dependent;
import com.skillstorm.taxprep.server.repositories.DependentRepository;

@Service
public class DependentService {
  
  @Autowired
  DependentRepository dependentRepository;

  public List<Dependent> findDependentsByTaxInfoId(int taxInfoId) {
    Optional<List<Dependent>> dependents = dependentRepository.findByTaxInfo_Id(taxInfoId);

    if (dependents.isPresent()) {
      return dependents.get();
    }

    return null;
  }

  public Dependent findDependentById(int dependentId) {
    Optional<Dependent> dependent = dependentRepository.findById(dependentId);

    if (dependent.isPresent()) {
      return dependent.get();
    }

    return null;
  }

  public Dependent saveDependent(Dependent dependent) {
    return dependentRepository.save(dependent);
  }

  public void deleteDependent(Dependent dependent) {
    dependentRepository.delete(dependent);
  }
}
