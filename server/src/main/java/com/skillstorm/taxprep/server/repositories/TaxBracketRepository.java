package com.skillstorm.taxprep.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillstorm.taxprep.server.models.TaxBracket;

public interface TaxBracketRepository extends JpaRepository<TaxBracket, Integer> {
  
}
