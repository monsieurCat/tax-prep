package com.skillstorm.taxprep.server.dtos;

import java.math.BigDecimal;

public class TaxInfoDTO {
  private FilingStatusDTO filingStatus;
  private int dependents;
  private BigDecimal annualIncome = BigDecimal.ZERO;
  private BigDecimal withholdings = BigDecimal.ZERO;
  private BigDecimal deductions = BigDecimal.ZERO;
  private BigDecimal tuitionAndFees = BigDecimal.ZERO;
  
  
  public FilingStatusDTO getFilingStatus() {
    return filingStatus;
  }
  public void setFilingStatus(FilingStatusDTO filingStatus) {
    this.filingStatus = filingStatus;
  }
  public int getDependents() {
    return dependents;
  }
  public void setDependents(int dependents) {
    this.dependents = dependents;
  }
  public BigDecimal getAnnualIncome() {
    return annualIncome;
  }
  public void setAnnualIncome(BigDecimal annualIncome) {
    this.annualIncome = annualIncome;
  }
  public BigDecimal getWithholdings() {
    return withholdings;
  }
  public void setWithholdings(BigDecimal withholdings) {
    this.withholdings = withholdings;
  }
  public BigDecimal getDeductions() {
    return deductions;
  }
  public void setDeductions(BigDecimal deductions) {
    this.deductions = deductions;
  }
  public BigDecimal getTuitionAndFees() {
    return tuitionAndFees;
  }
  public void setTuitionAndFees(BigDecimal tuitionAndFees) {
    this.tuitionAndFees = tuitionAndFees;
  }
}

