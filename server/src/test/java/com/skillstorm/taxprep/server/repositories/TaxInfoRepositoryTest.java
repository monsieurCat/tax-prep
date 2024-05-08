package com.skillstorm.taxprep.server.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.skillstorm.taxprep.server.models.AppUser;
import com.skillstorm.taxprep.server.models.FilingStatus;
import com.skillstorm.taxprep.server.models.TaxInfo;

@DataJpaTest
public class TaxInfoRepositoryTest {

    @Autowired
    private TaxInfoRepository taxInfoRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private FilingStatusRepository filingStatusRepository;

    private AppUser testUser;
    private FilingStatus filingStatus;

    @BeforeEach
    public void setup() {
        // Create a test user
        testUser = new AppUser.AppUserBuilder()
            .username("testuser")
            .password("password")
            .email("test@example.com")
            .role("USER")
            .build();
        userRepository.save(testUser);
        
        // Create a filing status
        filingStatus = new FilingStatus("Single");
        filingStatusRepository.save(filingStatus);
    }

    @Test
    public void testSaveTaxInfo_ShouldSaveTaxInfo() {
        // Given
        TaxInfo taxInfo = createSampleTaxInfo();

        // When
        TaxInfo savedTaxInfo = taxInfoRepository.save(taxInfo);

        // Then
        assertNotNull(savedTaxInfo);
        assertEquals(testUser.getId(), savedTaxInfo.getUser().getId());
        // Additional assertions if needed
    }

    @Test
    public void testFindByUserId_WithExistingData_ShouldReturnTaxInfo() {
        // Given
        TaxInfo taxInfo = createSampleTaxInfo();
        taxInfoRepository.save(taxInfo);

        // When
        Optional<TaxInfo> foundTaxInfo = taxInfoRepository.findByUser_Id(testUser.getId());

        // Then
        assertTrue(foundTaxInfo.isPresent());
        assertEquals(testUser.getId(), foundTaxInfo.get().getUser().getId());
        // Additional assertions if needed
    }

    @Test
    public void testFindByUserId_WithNonExistingData_ShouldReturnEmptyOptional() {
        // Given
        int nonExistingUserId = 999;

        // When
        Optional<TaxInfo> foundTaxInfo = taxInfoRepository.findByUser_Id(nonExistingUserId);

        // Then
        assertFalse(foundTaxInfo.isPresent());
    }

    @Test
    public void testFindTaxInfoIdByUserId_WithExistingData_ShouldReturnTaxInfoId() {
        // Given
        TaxInfo taxInfo = createSampleTaxInfo();
        taxInfoRepository.save(taxInfo);

        // When
        Optional<Integer> foundTaxInfoId = taxInfoRepository.findTaxInfoIdByUserId(testUser.getId());

        // Then
        assertTrue(foundTaxInfoId.isPresent());
        // Additional assertions if needed
    }

    @Test
    public void testFindTaxInfoIdByUserId_WithNonExistingData_ShouldReturnEmptyOptional() {
        // Given
        int nonExistingUserId = 999;

        // When
        Optional<Integer> foundTaxInfoId = taxInfoRepository.findTaxInfoIdByUserId(nonExistingUserId);

        // Then
        assertFalse(foundTaxInfoId.isPresent());
    }

    private TaxInfo createSampleTaxInfo() {
      return new TaxInfo.TaxInfoBuilder()
          .user(testUser)
          .filingStatus(filingStatus)
          .numDependents(2)
          .mortgageInterest(BigDecimal.valueOf(1000))
          .donations(BigDecimal.valueOf(500))
          .propertyTax(BigDecimal.valueOf(2000))
          .medical(BigDecimal.valueOf(1000))
          .studentLoanInterest(BigDecimal.valueOf(200))
          .otherDeduction(BigDecimal.valueOf(300))
          .otherIncome(BigDecimal.valueOf(500))
          .build();
    }
}
