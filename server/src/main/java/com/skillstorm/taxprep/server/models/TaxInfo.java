package com.skillstorm.taxprep.server.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
  private FilingStatus filingStatus;

  @Column(name = "num_dependents")
  private int numDependents;

  @Column(name = "mortgage_interest")
  private BigDecimal mortgageInterest;

  @Column
  private BigDecimal donations;

  @Column(name = "property_tax")
  private BigDecimal propertyTax;

  @Column
  private BigDecimal medical;

  @Column(name = "student_loan_interest")
  private BigDecimal studentLoanInterest;

  @Column(name = "other_deduction")
  private BigDecimal otherDeduction;

  @Column(name = "other_income")
  private BigDecimal otherIncome;

  public TaxInfo() {
  }

  private TaxInfo(TaxInfoBuilder builder) {
    this.id = builder.taxInfo.id;
    this.user = builder.taxInfo.user;
    this.filingStatus = builder.taxInfo.filingStatus;
    this.numDependents = builder.taxInfo.numDependents;
    this.mortgageInterest = builder.taxInfo.mortgageInterest;
    this.donations = builder.taxInfo.donations;
    this.propertyTax = builder.taxInfo.propertyTax;
    this.medical = builder.taxInfo.medical;
    this.studentLoanInterest = builder.taxInfo.studentLoanInterest;
    this.otherDeduction = builder.taxInfo.otherDeduction;
    this.otherIncome = builder.taxInfo.otherIncome;
  }

  public static class TaxInfoBuilder {
    private final TaxInfo taxInfo;

    public TaxInfoBuilder() {
      this.taxInfo = new TaxInfo();
    }

    public TaxInfoBuilder id(int id) {
      this.taxInfo.id = id;
      return this;
    }

    public TaxInfoBuilder user(AppUser user) {
      this.taxInfo.user = user;
      return this;
    }

    public TaxInfoBuilder filingStatus(FilingStatus filingStatus) {
      this.taxInfo.filingStatus = filingStatus;
      return this;
    }

    public TaxInfoBuilder numDependents(int numDependents) {
      this.taxInfo.numDependents = numDependents;
      return this;
    }

    public TaxInfoBuilder mortgageInterest(BigDecimal mortgageInterest) {
      this.taxInfo.mortgageInterest = mortgageInterest;
      return this;
    }

    public TaxInfoBuilder donations(BigDecimal donations) {
      this.taxInfo.donations = donations;
      return this;
    }

    public TaxInfoBuilder propertyTax(BigDecimal propertyTax) {
      this.taxInfo.propertyTax = propertyTax;
      return this;
    }

    public TaxInfoBuilder medical(BigDecimal medical) {
      this.taxInfo.medical = medical;
      return this;
    }

    public TaxInfoBuilder studentLoanInterest(BigDecimal studentLoanInterest) {
      this.taxInfo.studentLoanInterest = studentLoanInterest;
      return this;
    }

    public TaxInfoBuilder otherDeduction(BigDecimal otherDeduction) {
      this.taxInfo.otherDeduction = otherDeduction;
      return this;
    }

    public TaxInfoBuilder otherIncome(BigDecimal otherIncome) {
      this.taxInfo.otherIncome = otherIncome;
      return this;
    }

    public TaxInfo build() {
      return this.taxInfo;
    }
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    result = prime * result + ((user == null) ? 0 : user.hashCode());
    result = prime * result + ((filingStatus == null) ? 0 : filingStatus.hashCode());
    result = prime * result + numDependents;
    result = prime * result + ((mortgageInterest == null) ? 0 : mortgageInterest.hashCode());
    result = prime * result + ((donations == null) ? 0 : donations.hashCode());
    result = prime * result + ((propertyTax == null) ? 0 : propertyTax.hashCode());
    result = prime * result + ((medical == null) ? 0 : medical.hashCode());
    result = prime * result + ((studentLoanInterest == null) ? 0 : studentLoanInterest.hashCode());
    result = prime * result + ((otherDeduction == null) ? 0 : otherDeduction.hashCode());
    result = prime * result + ((otherIncome == null) ? 0 : otherIncome.hashCode());
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
    if (filingStatus == null) {
      if (other.filingStatus != null)
        return false;
    } else if (!filingStatus.equals(other.filingStatus))
      return false;
    if (numDependents != other.numDependents)
      return false;
    if (mortgageInterest == null) {
      if (other.mortgageInterest != null)
        return false;
    } else if (!mortgageInterest.equals(other.mortgageInterest))
      return false;
    if (donations == null) {
      if (other.donations != null)
        return false;
    } else if (!donations.equals(other.donations))
      return false;
    if (propertyTax == null) {
      if (other.propertyTax != null)
        return false;
    } else if (!propertyTax.equals(other.propertyTax))
      return false;
    if (medical == null) {
      if (other.medical != null)
        return false;
    } else if (!medical.equals(other.medical))
      return false;
    if (studentLoanInterest == null) {
      if (other.studentLoanInterest != null)
        return false;
    } else if (!studentLoanInterest.equals(other.studentLoanInterest))
      return false;
    if (otherDeduction == null) {
      if (other.otherDeduction != null)
        return false;
    } else if (!otherDeduction.equals(other.otherDeduction))
      return false;
    if (otherIncome == null) {
      if (other.otherIncome != null)
        return false;
    } else if (!otherIncome.equals(other.otherIncome))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "TaxInfo [id=" + id + ", user=" + user + ", filingStatus=" + filingStatus + ", numDependents="
        + numDependents + ", mortgageInterest=" + mortgageInterest + ", donations=" + donations + ", propertyTax="
        + propertyTax + ", medical=" + medical + ", studentLoanInterest=" + studentLoanInterest + ", otherDeduction="
        + otherDeduction + ", otherIncome=" + otherIncome + "]";
  }
}
