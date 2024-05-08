package com.skillstorm.taxprep.server.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "filing_status")
public class FilingStatus {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @Column
  private String status;

  public FilingStatus() { }

  public FilingStatus(int id, String status) {
    this.id = id;
    this.status = status;
  }

  public FilingStatus(String status) {
    this.status = status;
  }

  public int getId() {
    return id;
  }

  public String getStatus() {
    return status;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    result = prime * result + ((status == null) ? 0 : status.hashCode());
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
    FilingStatus other = (FilingStatus) obj;
    if (id != other.id)
      return false;
    if (status == null) {
      if (other.status != null)
        return false;
    } else if (!status.equals(other.status))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "FilingStatus [id=" + id + ", status=" + status + "]";
  }
}
