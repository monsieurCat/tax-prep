package com.skillstorm.taxprep.server.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tax_brackets")
public class TaxBracket {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @ManyToOne
  @JoinColumn(name = "filing_status_id")
  private FilingStatus filing_status;

  @Column
  private BigDecimal rate;

  @Column
  private int min_income;

  @Column
  private int max_income;

  public TaxBracket(int id, FilingStatus filing_status, BigDecimal rate, int min_income, int max_income) {
    this.id = id;
    this.filing_status = filing_status;
    this.rate = rate;
    this.min_income = min_income;
    this.max_income = max_income;
  }

  public TaxBracket(FilingStatus filing_status, BigDecimal rate, int min_income, int max_income) {
    this.filing_status = filing_status;
    this.rate = rate;
    this.min_income = min_income;
    this.max_income = max_income;
  }

  public int getId() {
    return id;
  }

  public FilingStatus getFiling_status() {
    return filing_status;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public int getMin_income() {
    return min_income;
  }

  public int getMax_income() {
    return max_income;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    result = prime * result + ((filing_status == null) ? 0 : filing_status.hashCode());
    result = prime * result + ((rate == null) ? 0 : rate.hashCode());
    result = prime * result + min_income;
    result = prime * result + max_income;
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
    TaxBracket other = (TaxBracket) obj;
    if (id != other.id)
      return false;
    if (filing_status == null) {
      if (other.filing_status != null)
        return false;
    } else if (!filing_status.equals(other.filing_status))
      return false;
    if (rate == null) {
      if (other.rate != null)
        return false;
    } else if (!rate.equals(other.rate))
      return false;
    if (min_income != other.min_income)
      return false;
    if (max_income != other.max_income)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "TaxBracket [id=" + id + ", filing_status=" + filing_status + ", rate=" + rate + ", min_income=" + min_income
        + ", max_income=" + max_income + "]";
  }
}
