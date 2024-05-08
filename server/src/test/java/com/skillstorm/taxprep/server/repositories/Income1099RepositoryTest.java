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

import com.skillstorm.taxprep.server.models.Income1099;

@DataJpaTest
public class Income1099RepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private Income1099Repository income1099Repository;

    @Test
    public void testFindByTaxInfoId_WithExistingData_ShouldReturnCorrectList() {
        // Given
        int taxInfoId = 1;
        Income1099 income1099 = new Income1099.Builder()
                .taxInfoId(taxInfoId)
                .income(BigDecimal.valueOf(50000))
                .withholdings(BigDecimal.valueOf(5000))
                .employerEin("123456789")
                .employerStreet1("123 Main St")
                .employerCity("Anytown")
                .employerState("ST")
                .employerZipcode("12345")
                .build();
        entityManager.persist(income1099);
        entityManager.flush();

        // When
        Optional<List<Income1099>> result = income1099Repository.findByTaxInfoId(taxInfoId);

        // Then
        assertTrue(result.isPresent());
        List<Income1099> income1099List = result.get();
        assertEquals(1, income1099List.size());
        Income1099 foundIncome1099 = income1099List.get(0);
        assertEquals(income1099.getId(), foundIncome1099.getId());
        assertEquals(income1099.getTaxInfoId(), foundIncome1099.getTaxInfoId());
        assertEquals(income1099.getIncome(), foundIncome1099.getIncome());
        assertEquals(income1099.getWithholdings(), foundIncome1099.getWithholdings());
        assertEquals(income1099.getEmployerEin(), foundIncome1099.getEmployerEin());
        assertEquals(income1099.getEmployerStreet1(), foundIncome1099.getEmployerStreet1());
        assertEquals(income1099.getEmployerCity(), foundIncome1099.getEmployerCity());
        assertEquals(income1099.getEmployerState(), foundIncome1099.getEmployerState());
        assertEquals(income1099.getEmployerZipcode(), foundIncome1099.getEmployerZipcode());
    }

    @Test
    public void testFindByTaxInfoId_WithNonExistingData_ShouldReturnEmptyOptional() {
        // Given
        int nonExistingTaxInfoId = 999;

        // When
        Optional<List<Income1099>> result = income1099Repository.findByTaxInfoId(nonExistingTaxInfoId);

        // Then
        assertTrue(result.isPresent());
        List<Income1099> income1099List = result.get();
        assertEquals(0, income1099List.size());
    }
}
