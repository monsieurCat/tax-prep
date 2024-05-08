package com.skillstorm.taxprep.server.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.skillstorm.taxprep.server.models.IncomeW2;

@DataJpaTest
public class IncomeW2RepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IncomeW2Repository incomeW2Repository;

    @Test
    public void testFindByTaxInfoId_WithExistingData_ShouldReturnCorrectList() {
        // Given
        int taxInfoId = 1;
        IncomeW2 incomeW2 = new IncomeW2.Builder()
                .taxInfoId(taxInfoId)
                .income(BigDecimal.valueOf(50000))
                .withholdings(BigDecimal.valueOf(5000))
                .employerEin("123456789")
                .employerStreet1("123 Main St")
                .employerCity("Anytown")
                .employerState("ST")
                .employerZipcode("12345")
                .build();
        entityManager.persist(incomeW2);
        entityManager.flush();

        // When
        Optional<List<IncomeW2>> result = incomeW2Repository.findByTaxInfoId(taxInfoId);

        // Then
        assertTrue(result.isPresent());
        List<IncomeW2> incomeW2List = result.get();
        assertEquals(1, incomeW2List.size());
        IncomeW2 foundIncomeW2 = incomeW2List.get(0);
        assertEquals(incomeW2.getId(), foundIncomeW2.getId());
        assertEquals(incomeW2.getTaxInfoId(), foundIncomeW2.getTaxInfoId());
        assertEquals(incomeW2.getIncome(), foundIncomeW2.getIncome());
        assertEquals(incomeW2.getWithholdings(), foundIncomeW2.getWithholdings());
        assertEquals(incomeW2.getEmployerEin(), foundIncomeW2.getEmployerEin());
        assertEquals(incomeW2.getEmployerStreet1(), foundIncomeW2.getEmployerStreet1());
        assertEquals(incomeW2.getEmployerCity(), foundIncomeW2.getEmployerCity());
        assertEquals(incomeW2.getEmployerState(), foundIncomeW2.getEmployerState());
        assertEquals(incomeW2.getEmployerZipcode(), foundIncomeW2.getEmployerZipcode());
    }

    @Test
    public void testFindByTaxInfoId_WithNonExistingData_ShouldReturnEmptyOptional() {
        // Given
        int nonExistingTaxInfoId = 999;

        // When
        Optional<List<IncomeW2>> result = incomeW2Repository.findByTaxInfoId(nonExistingTaxInfoId);

        // Then
        assertTrue(result.isPresent());
        List<IncomeW2> incomeW2List = result.get();
        assertEquals(0, incomeW2List.size());
    }
}
