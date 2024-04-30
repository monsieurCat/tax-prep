package com.skillstorm.taxprep.server.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "dependents")
public class Dependent {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @ManyToOne
  @JoinColumn(name = "tax_info_id")
  private TaxInfo taxInfo;

  @Column
  private int age;

  public Dependent(int id, TaxInfo taxInfo, int age) {
    this.id = id;
    this.taxInfo = taxInfo;
    this.age = age;
  }

  public Dependent(TaxInfo taxInfo, int age) {
    this.taxInfo = taxInfo;
    this.age = age;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public TaxInfo gettaxInfo() {
    return taxInfo;
  }

  public void settaxInfo(TaxInfo taxInfo) {
    this.taxInfo = taxInfo;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    result = prime * result + ((taxInfo == null) ? 0 : taxInfo.hashCode());
    result = prime * result + age;
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
    Dependent other = (Dependent) obj;
    if (id != other.id)
      return false;
    if (taxInfo == null) {
      if (other.taxInfo != null)
        return false;
    } else if (!taxInfo.equals(other.taxInfo))
      return false;
    if (age != other.age)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Dependent [id=" + id + ", taxInfo=" + taxInfo + ", age=" + age + "]";
  }
  
}
