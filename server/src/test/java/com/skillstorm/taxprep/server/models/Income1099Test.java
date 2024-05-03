package com.skillstorm.taxprep.server.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class Income1099Test {

  @Test
  public void testBuilder() {
    Income1099 income1099 = new Income1099.Builder()
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

    assertEquals(123, income1099.getTaxInfoId());
    assertEquals(BigDecimal.valueOf(50000), income1099.getIncome());
    assertEquals(BigDecimal.valueOf(5000), income1099.getWithholdings());
    assertEquals("123456789", income1099.getEmployerEin());
    assertEquals("Street 1", income1099.getEmployerStreet1());
    assertEquals("Street 2", income1099.getEmployerStreet2());
    assertEquals("City", income1099.getEmployerCity());
    assertEquals("State", income1099.getEmployerState());
    assertEquals("12345", income1099.getEmployerZipcode());
  }

  @Test
  public void testGettersAndSetters() {
    Income1099 income1099 = new Income1099();

    // Set values using setters
    income1099.setId(1);
    income1099.setTaxInfoId(123);
    income1099.setIncome(BigDecimal.valueOf(50000));
    income1099.setWithholdings(BigDecimal.valueOf(5000));
    income1099.setEmployerEin("123456789");
    income1099.setEmployerStreet1("Street 1");
    income1099.setEmployerStreet2("Street 2");
    income1099.setEmployerCity("City");
    income1099.setEmployerState("State");
    income1099.setEmployerZipcode("12345");

    // Check values using getters
    assertEquals(1, income1099.getId());
    assertEquals(123, income1099.getTaxInfoId());
    assertEquals(BigDecimal.valueOf(50000), income1099.getIncome());
    assertEquals(BigDecimal.valueOf(5000), income1099.getWithholdings());
    assertEquals("123456789", income1099.getEmployerEin());
    assertEquals("Street 1", income1099.getEmployerStreet1());
    assertEquals("Street 2", income1099.getEmployerStreet2());
    assertEquals("City", income1099.getEmployerCity());
    assertEquals("State", income1099.getEmployerState());
    assertEquals("12345", income1099.getEmployerZipcode());
  }

  @Test
  public void testEqualsAndHashCode() {
    Income1099 income10991 = new Income1099.Builder()
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

    Income1099 income10992 = new Income1099.Builder()
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

    Income1099 income10993 = new Income1099.Builder()
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
    assertEquals(income10991, income10992);
    assertNotEquals(income10991, income10993);

    // Test hashCode method
    assertEquals(income10991.hashCode(), income10992.hashCode());
    assertNotEquals(income10991.hashCode(), income10993.hashCode());
  }
}
