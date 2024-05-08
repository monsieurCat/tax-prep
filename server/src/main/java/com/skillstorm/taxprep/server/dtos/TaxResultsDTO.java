package com.skillstorm.taxprep.server.dtos;


public class TaxResultsDTO {
  private double incomeW2;
  private double income1099;
  private double totalIncome;
  private double deductions;
  private double taxableIncome;

  private double marginalTaxRate;
  private double effectiveTaxRate;

  private double taxWithheld;

  private double childTaxCredit;
  private double earnedIncomeTaxCredit;

  private double totalTaxAmount;
  private double finalTaxAmount;

  public double getIncomeW2() {
    return incomeW2;
  }

  public void setIncomeW2(double incomeW2) {
    this.incomeW2 = incomeW2;
  }

  public double getIncome1099() {
    return income1099;
  }

  public void setIncome1099(double income1099) {
    this.income1099 = income1099;
  }

  public double getTotalIncome() {
    return totalIncome;
  }

  public void setTotalIncome(double totalIncome) {
    this.totalIncome = totalIncome;
  }

  public double getDeductions() {
    return deductions;
  }

  public void setDeductions(double deductions) {
    this.deductions = deductions;
  }

  public double getTaxableIncome() {
    return taxableIncome;
  }

  public void setTaxableIncome(double taxableIncome) {
    this.taxableIncome = taxableIncome;
  }

  public double getMarginalTaxRate() {
    return marginalTaxRate;
  }

  public void setMarginalTaxRate(double marginalTaxRate) {
    this.marginalTaxRate = marginalTaxRate;
  }

  public double getEffectiveTaxRate() {
    return effectiveTaxRate;
  }

  public void setEffectiveTaxRate(double effectiveTaxRate) {
    this.effectiveTaxRate = effectiveTaxRate;
  }

  public double getTaxWithheld() {
    return taxWithheld;
  }

  public void setTaxWithheld(double taxWithheld) {
    this.taxWithheld = taxWithheld;
  }

  public double getChildTaxCredit() {
    return childTaxCredit;
  }

  public void setChildTaxCredit(double childTaxCredit) {
    this.childTaxCredit = childTaxCredit;
  }

  public double getEarnedIncomeTaxCredit() {
    return earnedIncomeTaxCredit;
  }

  public void setEarnedIncomeTaxCredit(double earnedIncomeTaxCredit) {
    this.earnedIncomeTaxCredit = earnedIncomeTaxCredit;
  }

  public double getTotalTaxAmount() {
    return totalTaxAmount;
  }

  public void setTotalTaxAmount(double totalTaxAmount) {
    this.totalTaxAmount = totalTaxAmount;
  }

  public double getFinalTaxAmount() {
    return finalTaxAmount;
  }

  public void setFinalTaxAmount(double finalTaxAmount) {
    this.finalTaxAmount = finalTaxAmount;
  }
}
