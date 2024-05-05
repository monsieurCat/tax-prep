package com.skillstorm.taxprep.server.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.taxprep.server.models.FilingStatus;

@Repository
public interface FilingStatusRepository extends JpaRepository<FilingStatus, Integer> {
  Optional<FilingStatus> findByStatus(String status);
}
