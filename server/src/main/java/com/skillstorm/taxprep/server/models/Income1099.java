package com.skillstorm.taxprep.server.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "income_1099")
public class Income1099 {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @Column(name = "tax_info_id")
  private int taxInfoId;

  @Column
  private BigDecimal income;

  @Column
  private BigDecimal withholdings;

  @Column(name = "employer_ein")
  private String employerEin;

  @Column(name = "employer_street_1")
  private String employerStreet1;

  @Column(name = "employer_street_2")
  private String employerStreet2;

  @Column(name = "employer_city")
  private String employerCity;

  @Column(name = "employer_state")
  private String employerState;

  @Column(name = "employer_zipcode")
  private String employerZipcode;

  // Private constructor to prevent direct instantiation
  private Income1099() { }

  

  public int getId() {
    return id;
  }



  public void setId(int id) {
    this.id = id;
  }



  public int getTaxInfoId() {
    return taxInfoId;
  }



  public void setTaxInfoId(int taxInfoId) {
    this.taxInfoId = taxInfoId;
  }



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

  // Builder class
  public static class Builder {
      private int taxInfoId;
      private BigDecimal income;
      private BigDecimal withholdings;
      private String employerEin;
      private String employerStreet1;
      private String employerStreet2;
      private String employerCity;
      private String employerState;
      private String employerZipcode;

      public Builder() {
      }

      public Builder taxInfoId(int taxInfoId) {
          this.taxInfoId = taxInfoId;
          return this;
      }

      public Builder income(BigDecimal income) {
          this.income = income;
          return this;
      }

      public Builder withholdings(BigDecimal withholdings) {
          this.withholdings = withholdings;
          return this;
      }

      public Builder employerEin(String employerEin) {
          this.employerEin = employerEin;
          return this;
      }

      public Builder employerStreet1(String employerStreet1) {
          this.employerStreet1 = employerStreet1;
          return this;
      }

      public Builder employerStreet2(String employerStreet2) {
          this.employerStreet2 = employerStreet2;
          return this;
      }

      public Builder employerCity(String employerCity) {
          this.employerCity = employerCity;
          return this;
      }

      public Builder employerState(String employerState) {
          this.employerState = employerState;
          return this;
      }

      public Builder employerZipcode(String employerZipcode) {
          this.employerZipcode = employerZipcode;
          return this;
      }

      public Income1099 build() {
          Income1099 income1099 = new Income1099();
          income1099.taxInfoId = this.taxInfoId;
          income1099.income = this.income;
          income1099.withholdings = this.withholdings;
          income1099.employerEin = this.employerEin;
          income1099.employerStreet1 = this.employerStreet1;
          income1099.employerStreet2 = this.employerStreet2;
          income1099.employerCity = this.employerCity;
          income1099.employerState = this.employerState;
          income1099.employerZipcode = this.employerZipcode;
          return income1099;
      }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    result = prime * result + taxInfoId;
    result = prime * result + ((income == null) ? 0 : income.hashCode());
    result = prime * result + ((withholdings == null) ? 0 : withholdings.hashCode());
    result = prime * result + ((employerEin == null) ? 0 : employerEin.hashCode());
    result = prime * result + ((employerStreet1 == null) ? 0 : employerStreet1.hashCode());
    result = prime * result + ((employerStreet2 == null) ? 0 : employerStreet2.hashCode());
    result = prime * result + ((employerCity == null) ? 0 : employerCity.hashCode());
    result = prime * result + ((employerState == null) ? 0 : employerState.hashCode());
    result = prime * result + ((employerZipcode == null) ? 0 : employerZipcode.hashCode());
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
    Income1099 other = (Income1099) obj;
    if (id != other.id)
      return false;
    if (taxInfoId != other.taxInfoId)
      return false;
    if (income == null) {
      if (other.income != null)
        return false;
    } else if (!income.equals(other.income))
      return false;
    if (withholdings == null) {
      if (other.withholdings != null)
        return false;
    } else if (!withholdings.equals(other.withholdings))
      return false;
    if (employerEin == null) {
      if (other.employerEin != null)
        return false;
    } else if (!employerEin.equals(other.employerEin))
      return false;
    if (employerStreet1 == null) {
      if (other.employerStreet1 != null)
        return false;
    } else if (!employerStreet1.equals(other.employerStreet1))
      return false;
    if (employerStreet2 == null) {
      if (other.employerStreet2 != null)
        return false;
    } else if (!employerStreet2.equals(other.employerStreet2))
      return false;
    if (employerCity == null) {
      if (other.employerCity != null)
        return false;
    } else if (!employerCity.equals(other.employerCity))
      return false;
    if (employerState == null) {
      if (other.employerState != null)
        return false;
    } else if (!employerState.equals(other.employerState))
      return false;
    if (employerZipcode == null) {
      if (other.employerZipcode != null)
        return false;
    } else if (!employerZipcode.equals(other.employerZipcode))
      return false;
    return true;
  }



  @Override
  public String toString() {
    return "Income1099 [id=" + id + ", taxInfoId=" + taxInfoId + ", income=" + income + ", withholdings=" + withholdings
        + ", employerEin=" + employerEin + ", employerStreet1=" + employerStreet1 + ", employerStreet2="
        + employerStreet2 + ", employerCity=" + employerCity + ", employerState=" + employerState + ", employerZipcode="
        + employerZipcode + "]";
  }

  
}
