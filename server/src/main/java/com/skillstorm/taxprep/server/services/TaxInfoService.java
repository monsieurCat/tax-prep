package com.skillstorm.taxprep.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.taxprep.server.exceptions.NotFoundException;
import com.skillstorm.taxprep.server.models.FilingStatus;
import com.skillstorm.taxprep.server.models.Income1099;
import com.skillstorm.taxprep.server.models.IncomeW2;
import com.skillstorm.taxprep.server.models.TaxInfo;
import com.skillstorm.taxprep.server.repositories.FilingStatusRepository;
import com.skillstorm.taxprep.server.repositories.Income1099Repository;
import com.skillstorm.taxprep.server.repositories.IncomeW2Repository;
import com.skillstorm.taxprep.server.repositories.TaxInfoRepository;

@Service
public class TaxInfoService {
  
  @Autowired
  private TaxInfoRepository taxInfoRepository;

  @Autowired
  private Income1099Repository income1099Repository;

  @Autowired
  private IncomeW2Repository incomeW2Repository;

  @Autowired
  FilingStatusRepository filingStatusRepository;

  public TaxInfo findByTaxInfoId(int taxInfoId) {
    Optional<TaxInfo> taxInfo = taxInfoRepository.findById(taxInfoId);

    if (taxInfo.isPresent()) {
      return taxInfo.get();
    }

    return null;
  }

  public TaxInfo findTaxInfoByUserId(int userId) {
    Optional<TaxInfo> taxInfo = taxInfoRepository.findByUser_Id(userId);

    if (taxInfo.isPresent()) {
      return taxInfo.get();
    }

    return null;
  }

  public int findTaxInfoIdByUserId(int userId) {
    Optional<Integer> taxInfoId = taxInfoRepository.findTaxInfoIdByUserId(userId);

    if (taxInfoId.isPresent()) {
      return taxInfoId.get();
    } else {
      throw new NotFoundException("Tax info not found for user with ID: " + userId);
    }
  }

  public List<Income1099> find1099IncomeByTaxInfoId(int taxInfoId) {
    Optional<List<Income1099>> incomes = income1099Repository.findByTaxInfoId(taxInfoId);

    if (incomes.isPresent()) {
      return incomes.get();
    }

    return null;
  }

  public List<IncomeW2> findW2IncomeByTaxInfoId(int taxInfoId) {
    Optional<List<IncomeW2>> incomes = incomeW2Repository.findByTaxInfoId(taxInfoId);

    if (incomes.isPresent()) {
      return incomes.get();
    }

    return null;
  }

  public FilingStatus findFilingStatusByTaxInfoId(int taxInfoId) {
    Optional<TaxInfo> taxInfo = taxInfoRepository.findById(taxInfoId);
    TaxInfo foundTaxInfo;

    if (taxInfo.isPresent()) {
      foundTaxInfo = taxInfo.get();
    } else {
      return null;
    }

    return foundTaxInfo.getFilingStatus();
  }

  public TaxInfo saveTaxInfo(TaxInfo taxInfo) {
    return taxInfoRepository.save(taxInfo);
  }

  public void deleteTaxInfo(TaxInfo taxInfo) {
    taxInfoRepository.delete(taxInfo);
  }
}
