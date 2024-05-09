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

import com.skillstorm.taxprep.server.models.IncomeW2;
import com.skillstorm.taxprep.server.repositories.IncomeW2Repository;

public class IncomeW2ServiceTest {

    @Mock
    private IncomeW2Repository IncomeW2Repository;

    @InjectMocks
    private IncomeW2Service IncomeW2Service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllIncome() {
        List<IncomeW2> incomeList = new ArrayList<>();
        incomeList.add(createIncomeW2(1));
        incomeList.add(createIncomeW2(2));
        when(IncomeW2Repository.findAll()).thenReturn(incomeList);

        List<IncomeW2> result = IncomeW2Service.getAllIncome();

        assertEquals(2, result.size());
    }

    @Test
    void testGetIncomeById() {
        IncomeW2 income = createIncomeW2(1);
        when(IncomeW2Repository.findById(1)).thenReturn(Optional.of(income));

        IncomeW2 result = IncomeW2Service.getIncomeById(1);

        assertEquals(income, result);
    }

    @Test
    void testSaveOrUpdateIncome() {
        IncomeW2 income = createIncomeW2(1);
        when(IncomeW2Repository.save(income)).thenReturn(income);

        IncomeW2 result = IncomeW2Service.saveOrUpdateIncome(income);

        assertEquals(income, result);
    }

    @Test
    void testDeleteIncomeById() {
        int id = 1;

        IncomeW2Service.deleteIncomeById(id);

        verify(IncomeW2Repository, times(1)).deleteById(id);
    }

    private IncomeW2 createIncomeW2(int id) {
        IncomeW2 income = new IncomeW2();
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
