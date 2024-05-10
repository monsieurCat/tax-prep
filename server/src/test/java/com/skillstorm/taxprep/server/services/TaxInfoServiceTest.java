package com.skillstorm.taxprep.server.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.skillstorm.taxprep.server.models.TaxInfo;
import com.skillstorm.taxprep.server.models.Income1099;
import com.skillstorm.taxprep.server.models.IncomeW2;
import com.skillstorm.taxprep.server.models.AppUser;
import com.skillstorm.taxprep.server.models.FilingStatus;
import com.skillstorm.taxprep.server.repositories.TaxInfoRepository;
import com.skillstorm.taxprep.server.repositories.Income1099Repository;
import com.skillstorm.taxprep.server.repositories.IncomeW2Repository;
import com.skillstorm.taxprep.server.repositories.FilingStatusRepository;

public class TaxInfoServiceTest {

    @Mock
    private TaxInfoRepository taxInfoRepository;

    @Mock
    private Income1099Repository income1099Repository;

    @Mock
    private IncomeW2Repository incomeW2Repository;

    @Mock
    private FilingStatusRepository filingStatusRepository;

    @InjectMocks
    private TaxInfoService taxInfoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByTaxInfoId_ExistingTaxInfoId_ReturnsTaxInfo() {
        TaxInfo taxInfo = new TaxInfo();
        taxInfo.setId(1);
        when(taxInfoRepository.findById(1)).thenReturn(Optional.of(taxInfo));

        TaxInfo foundTaxInfo = taxInfoService.findByTaxInfoId(1);

        assertEquals(taxInfo, foundTaxInfo);
    }

    @Test
    void testFindTaxInfoByUserId_ExistingUserId_ReturnsTaxInfo() {
        TaxInfo taxInfo = new TaxInfo();
        taxInfo.setUser(new AppUser());
        when(taxInfoRepository.findByUser_Id(1)).thenReturn(Optional.of(taxInfo));

        TaxInfo foundTaxInfo = taxInfoService.findTaxInfoByUserId(1);

        assertEquals(taxInfo, foundTaxInfo);
    }

    @Test
    void testFindTaxInfoIdByUserId_ExistingUserId_ReturnsTaxInfoId() {
        when(taxInfoRepository.findTaxInfoIdByUserId(1)).thenReturn(Optional.of(1));

        int foundTaxInfoId = taxInfoService.findTaxInfoIdByUserId(1);

        assertEquals(1, foundTaxInfoId);
    }

    @Test
    void testFind1099IncomeByTaxInfoId_ExistingTaxInfoId_ReturnsIncomeList() {
        int taxInfoId = 1;
        List<Income1099> expectedIncomes = Arrays.asList(new Income1099(), new Income1099());
        when(income1099Repository.findByTaxInfoId(taxInfoId)).thenReturn(Optional.of(expectedIncomes));

        List<Income1099> actualIncomes = taxInfoService.find1099IncomeByTaxInfoId(taxInfoId);

        assertEquals(expectedIncomes, actualIncomes);
    }

    @Test
    void testFindW2IncomeByTaxInfoId_ExistingTaxInfoId_ReturnsIncomeList() {
        int taxInfoId = 1;
        List<IncomeW2> expectedIncomes = Arrays.asList(new IncomeW2(), new IncomeW2());
        when(incomeW2Repository.findByTaxInfoId(taxInfoId)).thenReturn(Optional.of(expectedIncomes));

        List<IncomeW2> actualIncomes = taxInfoService.findW2IncomeByTaxInfoId(taxInfoId);

        assertEquals(expectedIncomes, actualIncomes);
    }

    @Test
    void testFindFilingStatusByTaxInfoId_ExistingTaxInfoId_ReturnsFilingStatus() {
        int taxInfoId = 1;
        FilingStatus expectedFilingStatus = new FilingStatus();
        TaxInfo taxInfo = new TaxInfo();
        taxInfo.setId(taxInfoId);
        taxInfo.setFilingStatus(expectedFilingStatus);
        when(taxInfoRepository.findById(taxInfoId)).thenReturn(Optional.of(taxInfo));

        FilingStatus actualFilingStatus = taxInfoService.findFilingStatusByTaxInfoId(taxInfoId);

        assertEquals(expectedFilingStatus, actualFilingStatus);
    }

    @Test
    void testSaveTaxInfo() {
        TaxInfo taxInfo = new TaxInfo();
        taxInfo.setId(1);
        when(taxInfoRepository.save(taxInfo)).thenReturn(taxInfo);

        TaxInfo savedTaxInfo = taxInfoService.saveTaxInfo(taxInfo);

        assertEquals(taxInfo, savedTaxInfo);
    }

    @Test
    void testSaveW2Income() {
        int taxInfoId = 1;
        List<IncomeW2> incomesW2 = Arrays.asList(new IncomeW2(), new IncomeW2());
        when(incomeW2Repository.save(any(IncomeW2.class))).thenAnswer(invocation -> invocation.getArgument(0));

        List<IncomeW2> savedIncomesW2 = taxInfoService.saveW2Income(incomesW2, taxInfoId);

        assertEquals(incomesW2, savedIncomesW2);
        for (IncomeW2 income : savedIncomesW2) {
            assertEquals(taxInfoId, income.getTaxInfoId());
        }
    }

    @Test
    void testSave1099Income() {
        int taxInfoId = 1;
        List<Income1099> incomes1099 = Arrays.asList(new Income1099(), new Income1099());
        when(income1099Repository.save(any(Income1099.class))).thenAnswer(invocation -> invocation.getArgument(0));

        List<Income1099> savedIncomes1099 = taxInfoService.save1099Income(incomes1099, taxInfoId);

        assertEquals(incomes1099, savedIncomes1099);
        for (Income1099 income : savedIncomes1099) {
            assertEquals(taxInfoId, income.getTaxInfoId());
        }
    }

    @Test
    void testDeleteTaxInfo() {
        TaxInfo taxInfo = new TaxInfo();
        taxInfo.setId(1);

        assertDoesNotThrow(() -> taxInfoService.deleteTaxInfo(taxInfo));
    }
}
