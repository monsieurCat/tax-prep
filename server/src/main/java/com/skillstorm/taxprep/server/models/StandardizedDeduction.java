package com.skillstorm.taxprep.server.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "standardized_deduction")
public class StandardizedDeduction {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @OneToOne
  @JoinColumn(name = "filing_status_id")
  private FilingStatus filingStatus;

  @Column(name = "deduction_amount")
  private int deductionAmount;

  public StandardizedDeduction(int id, FilingStatus filingStatus, int deductionAmount) {
    this.id = id;
    this.filingStatus = filingStatus;
    this.deductionAmount = deductionAmount;
  }

  public StandardizedDeduction(FilingStatus filingStatus, int deductionAmount) {
    this.filingStatus = filingStatus;
    this.deductionAmount = deductionAmount;
  }

  public StandardizedDeduction() { }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public FilingStatus getFilingStatus() {
    return filingStatus;
  }

  public void setFilingStatus(FilingStatus filingStatus) {
    this.filingStatus = filingStatus;
  }

  public int getDeductionAmount() {
    return deductionAmount;
  }

  public void setDeductionAmount(int deductionAmount) {
    this.deductionAmount = deductionAmount;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    result = prime * result + ((filingStatus == null) ? 0 : filingStatus.hashCode());
    result = prime * result + deductionAmount;
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
    StandardizedDeduction other = (StandardizedDeduction) obj;
    if (id != other.id)
      return false;
    if (filingStatus == null) {
      if (other.filingStatus != null)
        return false;
    } else if (!filingStatus.equals(other.filingStatus))
      return false;
    if (deductionAmount != other.deductionAmount)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "StandardizedDeduction [id=" + id + ", filingStatus=" + filingStatus + ", deductionAmount=" + deductionAmount
        + "]";
  }
}
