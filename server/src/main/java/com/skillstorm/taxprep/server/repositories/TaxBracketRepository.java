package com.skillstorm.taxprep.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.taxprep.server.models.TaxBracket;

@Repository
public interface TaxBracketRepository extends JpaRepository<TaxBracket, Integer> {
  
}
