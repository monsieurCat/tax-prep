package com.skillstorm.taxprep.server.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.taxprep.server.exceptions.NotFoundException;
import com.skillstorm.taxprep.server.models.FilingStatus;
import com.skillstorm.taxprep.server.repositories.FilingStatusRepository;

@Service
public class FilingStatusService {
  
  @Autowired
  private FilingStatusRepository filingStatusRepository;

  public FilingStatus getByStatus(String status) {
    Optional<FilingStatus> filingStatus = filingStatusRepository.findByStatus(status);

    if (filingStatus.isPresent()) {
      return filingStatus.get();
    } else {
      throw new NotFoundException("Filing status: " + status + " not found");
    }
  }
}
