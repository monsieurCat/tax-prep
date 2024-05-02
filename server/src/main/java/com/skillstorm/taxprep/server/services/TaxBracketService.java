package com.skillstorm.taxprep.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.taxprep.server.models.TaxBracket;
import com.skillstorm.taxprep.server.repositories.TaxBracketRepository;

@Service
public class TaxBracketService {
  
  @Autowired
  TaxBracketRepository taxBracketRepository;

  public List<TaxBracket> findByFilingStatusID(int id) {
    List<TaxBracket> taxBrackets = taxBracketRepository.findByFilingStatus_Id(id);
    return taxBrackets;
  }
}
