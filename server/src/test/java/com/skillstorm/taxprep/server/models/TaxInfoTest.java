package com.skillstorm.taxprep.server.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TaxInfoTest {

  @Mock
  private AppUser user;

  @Mock
  private FilingStatus filingStatus;

  @InjectMocks
  private TaxInfo.TaxInfoBuilder taxInfoBuilder;

  private TaxInfo taxInfo;

  @BeforeEach
  public void setup() {
    taxInfoBuilder = new TaxInfo.TaxInfoBuilder();
    taxInfo = taxInfoBuilder
      .id(1)
      .user(user)
      .filingStatus(filingStatus)
      .mortgageInterest(BigDecimal.valueOf(1000))
      .donations(BigDecimal.valueOf(500))
      .propertyTax(BigDecimal.valueOf(200))
      .medical(BigDecimal.valueOf(300))
      .studentLoanInterest(BigDecimal.valueOf(400))
      .build();
  }

  @Test
  public void testBuilderPattern() {
    assertNotNull(taxInfo);
    assertEquals(1, taxInfo.getId());
    assertEquals(user, taxInfo.getUser());
    assertEquals(filingStatus, taxInfo.getFilingStatus());
    assertEquals(BigDecimal.valueOf(1000), taxInfo.getMortgageInterest());
    assertEquals(BigDecimal.valueOf(500), taxInfo.getDonations());
    assertEquals(BigDecimal.valueOf(200), taxInfo.getPropertyTax());
    assertEquals(BigDecimal.valueOf(300), taxInfo.getMedical());
    assertEquals(BigDecimal.valueOf(400), taxInfo.getStudentLoanInterest());
  }

  @Test
  public void testSettersAndGetters() {
    TaxInfo newTaxInfo = new TaxInfo();
    newTaxInfo.setId(2);
    newTaxInfo.setUser(user);
    newTaxInfo.setFilingStatus(filingStatus);
    newTaxInfo.setMortgageInterest(BigDecimal.valueOf(1500));
    newTaxInfo.setDonations(BigDecimal.valueOf(700));
    newTaxInfo.setPropertyTax(BigDecimal.valueOf(300));
    newTaxInfo.setMedical(BigDecimal.valueOf(400));
    newTaxInfo.setStudentLoanInterest(BigDecimal.valueOf(600));

    assertEquals(2, newTaxInfo.getId());
    assertEquals(user, newTaxInfo.getUser());
    assertEquals(filingStatus, newTaxInfo.getFilingStatus());
    assertEquals(BigDecimal.valueOf(1500), newTaxInfo.getMortgageInterest());
    assertEquals(BigDecimal.valueOf(700), newTaxInfo.getDonations());
    assertEquals(BigDecimal.valueOf(300), newTaxInfo.getPropertyTax());
    assertEquals(BigDecimal.valueOf(400), newTaxInfo.getMedical());
    assertEquals(BigDecimal.valueOf(600), newTaxInfo.getStudentLoanInterest());
  }

  @Test
  public void testNoArgConstructor() {
    TaxInfo newTaxInfo = new TaxInfo();
    assertNotNull(newTaxInfo);
    assertNull(newTaxInfo.getUser());
    assertNull(newTaxInfo.getFilingStatus());
    assertNull(newTaxInfo.getMortgageInterest());
    assertNull(newTaxInfo.getDonations());
    assertNull(newTaxInfo.getPropertyTax());
    assertNull(newTaxInfo.getMedical());
    assertNull(newTaxInfo.getStudentLoanInterest());
  }
}
