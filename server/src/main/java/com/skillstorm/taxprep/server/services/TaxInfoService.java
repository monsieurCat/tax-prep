package com.skillstorm.taxprep.server.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.taxprep.server.models.TaxInfo;
import com.skillstorm.taxprep.server.repositories.TaxInfoRepository;

@Service
public class TaxInfoService {
  
  @Autowired
  private TaxInfoRepository taxInfoRepository;

  public TaxInfo findTaxInfoByUserId(int userId) {
    Optional<TaxInfo> taxInfo = taxInfoRepository.findByUser_Id(userId);

    if (taxInfo.isPresent()) {
      return taxInfo.get();
    }

    return null;
  }

  public TaxInfo saveTaxInfo(TaxInfo taxInfo) {
    return taxInfoRepository.save(taxInfo);
  }

  public void deleteTaxInfo(TaxInfo taxInfo) {
    taxInfoRepository.delete(taxInfo);
  }
}
