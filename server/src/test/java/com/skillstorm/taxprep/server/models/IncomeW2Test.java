package com.skillstorm.taxprep.server.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class IncomeW2Test {

  @Test
  public void testBuilder() {
    IncomeW2 incomeW2 = new IncomeW2.Builder()
            .taxInfoId(123)
            .income(BigDecimal.valueOf(50000))
            .withholdings(BigDecimal.valueOf(5000))
            .employerEin("123456789")
            .employerStreet1("Street 1")
            .employerStreet2("Street 2")
            .employerCity("City")
            .employerState("State")
            .employerZipcode("12345")
            .build();

    assertEquals(123, incomeW2.getTaxInfoId());
    assertEquals(BigDecimal.valueOf(50000), incomeW2.getIncome());
    assertEquals(BigDecimal.valueOf(5000), incomeW2.getWithholdings());
    assertEquals("123456789", incomeW2.getEmployerEin());
    assertEquals("Street 1", incomeW2.getEmployerStreet1());
    assertEquals("Street 2", incomeW2.getEmployerStreet2());
    assertEquals("City", incomeW2.getEmployerCity());
    assertEquals("State", incomeW2.getEmployerState());
    assertEquals("12345", incomeW2.getEmployerZipcode());
  }

  @Test
  public void testGettersAndSetters() {
    IncomeW2 incomeW2 = new IncomeW2();

    // Set values using setters
    incomeW2.setId(1);
    incomeW2.setTaxInfoId(123);
    incomeW2.setIncome(BigDecimal.valueOf(50000));
    incomeW2.setWithholdings(BigDecimal.valueOf(5000));
    incomeW2.setEmployerEin("123456789");
    incomeW2.setEmployerStreet1("Street 1");
    incomeW2.setEmployerStreet2("Street 2");
    incomeW2.setEmployerCity("City");
    incomeW2.setEmployerState("State");
    incomeW2.setEmployerZipcode("12345");

    // Check values using getters
    assertEquals(1, incomeW2.getId());
    assertEquals(123, incomeW2.getTaxInfoId());
    assertEquals(BigDecimal.valueOf(50000), incomeW2.getIncome());
    assertEquals(BigDecimal.valueOf(5000), incomeW2.getWithholdings());
    assertEquals("123456789", incomeW2.getEmployerEin());
    assertEquals("Street 1", incomeW2.getEmployerStreet1());
    assertEquals("Street 2", incomeW2.getEmployerStreet2());
    assertEquals("City", incomeW2.getEmployerCity());
    assertEquals("State", incomeW2.getEmployerState());
    assertEquals("12345", incomeW2.getEmployerZipcode());
  }

  @Test
  public void testEqualsAndHashCode() {
    IncomeW2 incomeW21 = new IncomeW2.Builder()
            .taxInfoId(123)
            .income(BigDecimal.valueOf(50000))
            .withholdings(BigDecimal.valueOf(5000))
            .employerEin("123456789")
            .employerStreet1("Street 1")
            .employerStreet2("Street 2")
            .employerCity("City")
            .employerState("State")
            .employerZipcode("12345")
            .build();

    IncomeW2 incomeW22 = new IncomeW2.Builder()
            .taxInfoId(123)
            .income(BigDecimal.valueOf(50000))
            .withholdings(BigDecimal.valueOf(5000))
            .employerEin("123456789")
            .employerStreet1("Street 1")
            .employerStreet2("Street 2")
            .employerCity("City")
            .employerState("State")
            .employerZipcode("12345")
            .build();

    IncomeW2 incomeW23 = new IncomeW2.Builder()
            .taxInfoId(456)
            .income(BigDecimal.valueOf(60000))
            .withholdings(BigDecimal.valueOf(6000))
            .employerEin("987654321")
            .employerStreet1("Street 3")
            .employerStreet2("Street 4")
            .employerCity("City")
            .employerState("State")
            .employerZipcode("67890")
            .build();

    // Test equals method
    assertEquals(incomeW21, incomeW22);
    assertNotEquals(incomeW21, incomeW23);

    // Test hashCode method
    assertEquals(incomeW21.hashCode(), incomeW22.hashCode());
    assertNotEquals(incomeW21.hashCode(), incomeW23.hashCode());
  }
}
