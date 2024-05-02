package com.skillstorm.taxprep.server.models;

import java.math.BigDecimal;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tax_info")
public class TaxInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @OneToOne
  @JoinColumn(name = "user_id")
  private AppUser user;

  @ManyToOne
  @JoinColumn(name = "filing_status_id")
  private FilingStatus filing_status;

  /* @OneToMany(targetEntity = Dependent.class, cascade = CascadeType.REMOVE)
  @JsonIgnore
  private Set<Dependent> dependents; */

  @Column
  private BigDecimal annual_income;

  @Column
  private BigDecimal withholdings;

  @Column
  private BigDecimal deductions;

  @Column
  private BigDecimal tuition_and_fees;

  public TaxInfo() {
  }

  private TaxInfo(Builder builder) {
    this.filing_status = builder.filingStatus;
    this.annual_income = builder.annualIncome;
    this.withholdings = builder.withholdings;
    this.deductions = builder.deductions;
    this.tuition_and_fees = builder.tuitionAndFees;
}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public AppUser getUser() {
    return user;
  }

  public void setUser(AppUser user) {
    this.user = user;
  }

  public FilingStatus getFiling_status() {
    return filing_status;
  }

  public void setFiling_status(FilingStatus filing_status) {
    this.filing_status = filing_status;
  }

  public BigDecimal getAnnual_income() {
    return annual_income;
  }

  public void setAnnual_income(BigDecimal annual_income) {
    this.annual_income = annual_income;
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

  public BigDecimal getTuition_and_fees() {
    return tuition_and_fees;
  }

  public void setTuition_and_fees(BigDecimal tuition_and_fees) {
    this.tuition_and_fees = tuition_and_fees;
  }

  public static class Builder {
    private FilingStatus filingStatus;
    private BigDecimal annualIncome;
    private BigDecimal withholdings;
    private BigDecimal deductions;
    private BigDecimal tuitionAndFees;

    public Builder filingStatus(FilingStatus filingStatus) {
        this.filingStatus = filingStatus;
        return this;
    }

    public Builder annualIncome(BigDecimal annualIncome) {
        this.annualIncome = annualIncome;
        return this;
    }

    public Builder withholdings(BigDecimal withholdings) {
        this.withholdings = withholdings;
        return this;
    }

    public Builder deductions(BigDecimal deductions) {
        this.deductions = deductions;
        return this;
    }

    public Builder tuitionAndFees(BigDecimal tuitionAndFees) {
        this.tuitionAndFees = tuitionAndFees;
        return this;
    }

    public TaxInfo build() {
        return new TaxInfo(this);
    }
  }
  
}
