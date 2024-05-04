package com.skillstorm.taxprep.server.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.skillstorm.taxprep.server.models.Income1099;
import com.skillstorm.taxprep.server.repositories.Income1099Repository;

public class Income1099ServiceTest {

    @Mock
    private Income1099Repository income1099Repository;

    @InjectMocks
    private Income1099Service income1099Service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllIncome() {
        List<Income1099> incomeList = new ArrayList<>();
        incomeList.add(createIncome1099(1));
        incomeList.add(createIncome1099(2));
        when(income1099Repository.findAll()).thenReturn(incomeList);

        List<Income1099> result = income1099Service.getAllIncome();

        assertEquals(2, result.size());
    }

    @Test
    void testGetIncomeById() {
        Income1099 income = createIncome1099(1);
        when(income1099Repository.findById(1)).thenReturn(Optional.of(income));

        Income1099 result = income1099Service.getIncomeById(1);

        assertEquals(income, result);
    }

    @Test
    void testSaveOrUpdateIncome() {
        Income1099 income = createIncome1099(1);
        when(income1099Repository.save(income)).thenReturn(income);

        Income1099 result = income1099Service.saveOrUpdateIncome(income);

        assertEquals(income, result);
    }

    @Test
    void testDeleteIncomeById() {
        int id = 1;

        income1099Service.deleteIncomeById(id);

        verify(income1099Repository, times(1)).deleteById(id);
    }

    private Income1099 createIncome1099(int id) {
        Income1099 income = new Income1099();
        income.setId(id);
        income.setTaxInfoId(100 + id);
        income.setIncome(BigDecimal.valueOf(5000 + id));
        income.setWithholdings(BigDecimal.valueOf(500 + id));
        income.setEmployerEin("EIN" + id);
        income.setEmployerStreet1("Street1" + id);
        income.setEmployerStreet2("Street2" + id);
        income.setEmployerCity("City" + id);
        income.setEmployerState("State" + id);
        income.setEmployerZipcode("Zipcode" + id);
        return income;
    }
}
