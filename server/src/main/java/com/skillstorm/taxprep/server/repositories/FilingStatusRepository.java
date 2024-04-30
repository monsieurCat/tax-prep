package com.skillstorm.taxprep.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillstorm.taxprep.server.models.FilingStatus;

public interface FilingStatusRepository extends JpaRepository<FilingStatus, Integer> {
  
}
