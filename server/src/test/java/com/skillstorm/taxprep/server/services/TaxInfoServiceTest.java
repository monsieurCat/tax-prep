package com.skillstorm.taxprep.server.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.skillstorm.taxprep.server.models.FilingStatus;
import com.skillstorm.taxprep.server.models.Income1099;
import com.skillstorm.taxprep.server.models.IncomeW2;
import com.skillstorm.taxprep.server.models.TaxInfo;
import com.skillstorm.taxprep.server.repositories.FilingStatusRepository;
import com.skillstorm.taxprep.server.repositories.Income1099Repository;
import com.skillstorm.taxprep.server.repositories.IncomeW2Repository;
import com.skillstorm.taxprep.server.repositories.TaxInfoRepository;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringJUnitConfig
@SpringBootTest
class TaxInfoServiceTest {

    @Autowired
    private TaxInfoService taxInfoService;

    @MockBean
    private TaxInfoRepository taxInfoRepository;

    @MockBean
    private Income1099Repository income1099Repository;

    @MockBean
    private IncomeW2Repository incomeW2Repository;

    @MockBean
    private FilingStatusRepository filingStatusRepository;

    @Test
    void testFindByTaxInfoId_ExistingTaxInfoId_ReturnsTaxInfo() {
        // Arrange
        TaxInfo taxInfo = new TaxInfo();
        taxInfo.setId(1);
        when(taxInfoRepository.findById(1)).thenReturn(Optional.of(taxInfo));

        // Act
        TaxInfo foundTaxInfo = taxInfoService.findByTaxInfoId(1);

        // Assert
        assertEquals(taxInfo, foundTaxInfo);
    }

    @Test
    void testFindTaxInfoByUserId_ExistingUserId_ReturnsTaxInfo() {
        // Arrange
        TaxInfo taxInfo = new TaxInfo();
        taxInfo.setId(1);
        when(taxInfoRepository.findByUser_Id(1)).thenReturn(Optional.of(taxInfo));

        // Act
        TaxInfo foundTaxInfo = taxInfoService.findTaxInfoByUserId(1);

        // Assert
        assertEquals(taxInfo, foundTaxInfo);
    }

    @Test
    void testFindTaxInfoIdByUserId_ExistingUserId_ReturnsTaxInfoId() {
        // Arrange
        when(taxInfoRepository.findTaxInfoIdByUserId(1)).thenReturn(Optional.of(1));

        // Act
        int foundTaxInfoId = taxInfoService.findTaxInfoIdByUserId(1);

        // Assert
        assertEquals(1, foundTaxInfoId);
    }

    @Test
    void testFind1099IncomeByTaxInfoId_ExistingTaxInfoId_ReturnsIncomeList() {
        // Arrange
        List<Income1099> incomeList = new ArrayList<>();
        Income1099 income = new Income1099();
        incomeList.add(income);
        when(income1099Repository.findByTaxInfoId(1)).thenReturn(Optional.of(incomeList));

        // Act
        List<Income1099> foundIncomeList = taxInfoService.find1099IncomeByTaxInfoId(1);

        // Assert
        assertEquals(incomeList, foundIncomeList);
    }

    @Test
    void testFindW2IncomeByTaxInfoId_ExistingTaxInfoId_ReturnsIncomeList() {
        // Arrange
        List<IncomeW2> incomeList = new ArrayList<>();
        IncomeW2 income = new IncomeW2();
        incomeList.add(income);
        when(incomeW2Repository.findByTaxInfoId(1)).thenReturn(Optional.of(incomeList));

        // Act
        List<IncomeW2> foundIncomeList = taxInfoService.findW2IncomeByTaxInfoId(1);

        // Assert
        assertEquals(incomeList, foundIncomeList);
    }

    @Test
    void testFindFilingStatusByTaxInfoId_ExistingTaxInfoId_ReturnsFilingStatus() {
        // Arrange
        TaxInfo taxInfo = new TaxInfo();
        FilingStatus filingStatus = new FilingStatus();
        taxInfo.setFilingStatus(filingStatus);
        when(taxInfoRepository.findById(1)).thenReturn(Optional.of(taxInfo));

        // Act
        FilingStatus foundFilingStatus = taxInfoService.findFilingStatusByTaxInfoId(1);

        // Assert
        assertEquals(filingStatus, foundFilingStatus);
    }

    @Test
    void testSaveTaxInfo() {
        // Arrange
        TaxInfo taxInfo = new TaxInfo();
        when(taxInfoRepository.save(any(TaxInfo.class))).thenReturn(taxInfo);

        // Act
        TaxInfo savedTaxInfo = taxInfoService.saveTaxInfo(taxInfo);

        // Assert
        verify(taxInfoRepository, times(1)).save(taxInfo);
        assertEquals(taxInfo, savedTaxInfo);
    }

    @Test
    void testSaveW2Income() {
        // Arrange
        List<IncomeW2> incomesW2 = new ArrayList<>();
        IncomeW2 incomeW2 = new IncomeW2();
        incomesW2.add(incomeW2);
        when(incomeW2Repository.save(any(IncomeW2.class))).thenReturn(incomeW2);

        // Act
        List<IncomeW2> savedIncomesW2 = taxInfoService.saveW2Income(incomesW2, 1);

        // Assert
        assertEquals(incomesW2, savedIncomesW2);
    }

    @Test
    void testSave1099Income() {
        // Arrange
        List<Income1099> incomes1099 = new ArrayList<>();
        Income1099 income1099 = new Income1099();
        incomes1099.add(income1099);
        when(income1099Repository.save(any(Income1099.class))).thenReturn(income1099);

        // Act
        List<Income1099> savedIncomes1099 = taxInfoService.save1099Income(incomes1099, 1);

        // Assert
        assertEquals(incomes1099, savedIncomes1099);
    }

    @Test
    void testDeleteTaxInfo() {
        // Arrange
        TaxInfo taxInfo = new TaxInfo();

        // Act
        taxInfoService.deleteTaxInfo(taxInfo);

        // Assert
        verify(taxInfoRepository, times(1)).delete(taxInfo);
    }
}
