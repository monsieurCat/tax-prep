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
  private FilingStatus filingStatus;

  @Column
  private BigDecimal rate;

  @Column(name = "min_income")
  private int minIncome;

  @Column(name = "max_income")
  private int maxIncome;

  public TaxBracket(int id, FilingStatus filingStatus, BigDecimal rate, int minIncome, int maxIncome) {
    this.id = id;
    this.filingStatus = filingStatus;
    this.rate = rate;
    this.minIncome = minIncome;
    this.maxIncome = maxIncome;
  }

  public TaxBracket(FilingStatus filingStatus, BigDecimal rate, int minIncome, int maxIncome) {
    this.filingStatus = filingStatus;
    this.rate = rate;
    this.minIncome = minIncome;
    this.maxIncome = maxIncome;
  }

  public int getId() {
    return id;
  }

  public FilingStatus getFiling_status() {
    return filingStatus;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public int getMin_income() {
    return minIncome;
  }

  public int getMax_income() {
    return maxIncome;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    result = prime * result + ((filingStatus == null) ? 0 : filingStatus.hashCode());
    result = prime * result + ((rate == null) ? 0 : rate.hashCode());
    result = prime * result + minIncome;
    result = prime * result + maxIncome;
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
    if (filingStatus == null) {
      if (other.filingStatus != null)
        return false;
    } else if (!filingStatus.equals(other.filingStatus))
      return false;
    if (rate == null) {
      if (other.rate != null)
        return false;
    } else if (!rate.equals(other.rate))
      return false;
    if (minIncome != other.minIncome)
      return false;
    if (maxIncome != other.maxIncome)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "TaxBracket [id=" + id + ", filingStatus=" + filingStatus + ", rate=" + rate + ", minIncome=" + minIncome
        + ", maxIncome=" + maxIncome + "]";
  }
}
