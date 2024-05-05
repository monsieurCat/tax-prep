package com.skillstorm.taxprep.server.dtos;

import java.math.BigDecimal;
import java.util.List;

import com.skillstorm.taxprep.server.models.FilingStatus;
import com.skillstorm.taxprep.server.models.Income1099;
import com.skillstorm.taxprep.server.models.IncomeW2;

public class TaxInfoDTO {
  private FilingStatus filingStatus;
  private int numDependents = 0;
  private List<IncomeW2> incomeW2;
  private List<Income1099> income1099;
  private BigDecimal mortgageInterest = BigDecimal.ZERO;
  private BigDecimal donations = BigDecimal.ZERO;
  private BigDecimal propertyTax = BigDecimal.ZERO;
  private BigDecimal medical = BigDecimal.ZERO;
  private BigDecimal studentLoanInterest = BigDecimal.ZERO;
  private BigDecimal otherDeduction = BigDecimal.ZERO;
  private BigDecimal otherIncome = BigDecimal.ZERO;


  public FilingStatus getFilingStatus() {
    return filingStatus;
  }
  public void setFilingStatus(FilingStatus filingStatus) {
    this.filingStatus = filingStatus;
  }
  public int getNumDependents() {
    return numDependents;
  }
  public void setNumDependents(int numDependents) {
    this.numDependents = numDependents;
  }
  public List<IncomeW2> getIncomeW2() {
    return incomeW2;
  }
  public void setIncomeW2(List<IncomeW2> incomeW2) {
    this.incomeW2 = incomeW2;
  }
  public List<Income1099> getIncome1099() {
    return income1099;
  }
  public void setIncome1099(List<Income1099> income1099) {
    this.income1099 = income1099;
  }
  public BigDecimal getMortgageInterest() {
    return mortgageInterest;
  }
  public void setMortgageInterest(BigDecimal mortgageInterest) {
    this.mortgageInterest = mortgageInterest;
  }
  public BigDecimal getDonations() {
    return donations;
  }
  public void setDonations(BigDecimal donations) {
    this.donations = donations;
  }
  public BigDecimal getPropertyTax() {
    return propertyTax;
  }
  public void setPropertyTax(BigDecimal propertyTax) {
    this.propertyTax = propertyTax;
  }
  public BigDecimal getMedical() {
    return medical;
  }
  public void setMedical(BigDecimal medical) {
    this.medical = medical;
  }
  public BigDecimal getStudentLoanInterest() {
    return studentLoanInterest;
  }
  public void setStudentLoanInterest(BigDecimal studentLoanInterest) {
    this.studentLoanInterest = studentLoanInterest;
  }
  public BigDecimal getOtherDeduction() {
    return otherDeduction;
  }
  public void setOtherDeduction(BigDecimal otherDeduction) {
    this.otherDeduction = otherDeduction;
  }
  public BigDecimal getOtherIncome() {
    return otherIncome;
  }
  public void setOtherIncome(BigDecimal otherIncome) {
    this.otherIncome = otherIncome;
  }
  
  
}

