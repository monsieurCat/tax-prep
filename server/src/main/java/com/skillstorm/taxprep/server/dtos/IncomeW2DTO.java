package com.skillstorm.taxprep.server.dtos;

import java.math.BigDecimal;

public class IncomeW2DTO {
  private BigDecimal income;
  private BigDecimal withholdings;
  private String employerEin;
  private String employerStreet1;
  private String employerStreet2;
  private String employerCity;
  private String employerState;
  private String employerZipcode;

  
  public BigDecimal getIncome() {
    return income;
  }
  public void setIncome(BigDecimal income) {
    this.income = income;
  }
  public BigDecimal getWithholdings() {
    return withholdings;
  }
  public void setWithholdings(BigDecimal withholdings) {
    this.withholdings = withholdings;
  }
  public String getEmployerEin() {
    return employerEin;
  }
  public void setEmployerEin(String employerEin) {
    this.employerEin = employerEin;
  }
  public String getEmployerStreet1() {
    return employerStreet1;
  }
  public void setEmployerStreet1(String employerStreet1) {
    this.employerStreet1 = employerStreet1;
  }
  public String getEmployerStreet2() {
    return employerStreet2;
  }
  public void setEmployerStreet2(String employerStreet2) {
    this.employerStreet2 = employerStreet2;
  }
  public String getEmployerCity() {
    return employerCity;
  }
  public void setEmployerCity(String employerCity) {
    this.employerCity = employerCity;
  }
  public String getEmployerState() {
    return employerState;
  }
  public void setEmployerState(String employerState) {
    this.employerState = employerState;
  }
  public String getEmployerZipcode() {
    return employerZipcode;
  }
  public void setEmployerZipcode(String employerZipcode) {
    this.employerZipcode = employerZipcode;
  } 

  
}
