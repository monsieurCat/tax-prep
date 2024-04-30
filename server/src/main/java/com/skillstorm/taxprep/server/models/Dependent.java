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
  private TaxInfo tax_info;

  @Column
  private int age;

  public Dependent(int id, TaxInfo tax_info, int age) {
    this.id = id;
    this.tax_info = tax_info;
    this.age = age;
  }

  public Dependent(TaxInfo tax_info, int age) {
    this.tax_info = tax_info;
    this.age = age;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public TaxInfo getTax_info() {
    return tax_info;
  }

  public void setTax_info(TaxInfo tax_info) {
    this.tax_info = tax_info;
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
    result = prime * result + ((tax_info == null) ? 0 : tax_info.hashCode());
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
    if (tax_info == null) {
      if (other.tax_info != null)
        return false;
    } else if (!tax_info.equals(other.tax_info))
      return false;
    if (age != other.age)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Dependent [id=" + id + ", tax_info=" + tax_info + ", age=" + age + "]";
  }
  
}
