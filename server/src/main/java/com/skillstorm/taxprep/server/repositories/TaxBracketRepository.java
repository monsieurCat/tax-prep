package com.skillstorm.taxprep.server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.taxprep.server.models.TaxBracket;

@Repository
public interface TaxBracketRepository extends JpaRepository<TaxBracket, Integer> {
  
  public List<TaxBracket> findByFilingStatus_Id(int id);
}
