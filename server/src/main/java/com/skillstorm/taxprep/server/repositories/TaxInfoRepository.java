package com.skillstorm.taxprep.server.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillstorm.taxprep.server.models.TaxInfo;

public interface TaxInfoRepository extends JpaRepository<TaxInfo, Integer> {
  public Optional<TaxInfo> findByUser_Id(int userId);
}
