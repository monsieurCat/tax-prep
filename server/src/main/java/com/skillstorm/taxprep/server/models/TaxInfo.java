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

  @OneToMany(targetEntity = Dependent.class, cascade = CascadeType.REMOVE)
  @JsonIgnore
  private Set<Dependent> dependents;

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
    this.dependents = builder.dependents;
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

  public Set<Dependent> getDependents() {
    return dependents;
  }

  public void setDependents(Set<Dependent> dependents) {
    this.dependents = dependents;
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
    private Set<Dependent> dependents;
    private BigDecimal annualIncome;
    private BigDecimal withholdings;
    private BigDecimal deductions;
    private BigDecimal tuitionAndFees;

    public Builder filingStatus(FilingStatus filingStatus) {
        this.filingStatus = filingStatus;
        return this;
    }

    public Builder dependents(Set<Dependent> dependents) {
        this.dependents = dependents;
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    result = prime * result + ((user == null) ? 0 : user.hashCode());
    result = prime * result + ((filing_status == null) ? 0 : filing_status.hashCode());
    result = prime * result + ((dependents == null) ? 0 : dependents.hashCode());
    result = prime * result + ((annual_income == null) ? 0 : annual_income.hashCode());
    result = prime * result + ((withholdings == null) ? 0 : withholdings.hashCode());
    result = prime * result + ((deductions == null) ? 0 : deductions.hashCode());
    result = prime * result + ((tuition_and_fees == null) ? 0 : tuition_and_fees.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    TaxInfo other = (TaxInfo) obj;
    if (id != other.id)
      return false;
    if (user == null) {
      if (other.user != null)
        return false;
    } else if (!user.equals(other.user))
      return false;
    if (filing_status == null) {
      if (other.filing_status != null)
        return false;
    } else if (!filing_status.equals(other.filing_status))
      return false;
    if (dependents == null) {
      if (other.dependents != null)
        return false;
    } else if (!dependents.equals(other.dependents))
      return false;
    if (annual_income == null) {
      if (other.annual_income != null)
        return false;
    } else if (!annual_income.equals(other.annual_income))
      return false;
    if (withholdings == null) {
      if (other.withholdings != null)
        return false;
    } else if (!withholdings.equals(other.withholdings))
      return false;
    if (deductions == null) {
      if (other.deductions != null)
        return false;
    } else if (!deductions.equals(other.deductions))
      return false;
    if (tuition_and_fees == null) {
      if (other.tuition_and_fees != null)
        return false;
    } else if (!tuition_and_fees.equals(other.tuition_and_fees))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "TaxInfo [id=" + id + ", user=" + user + ", filing_status=" + filing_status + ", dependents=" + dependents
        + ", annual_income=" + annual_income + ", withholdings=" + withholdings + ", deductions=" + deductions
        + ", tuition_and_fees=" + tuition_and_fees + "]";
  }
  
}
