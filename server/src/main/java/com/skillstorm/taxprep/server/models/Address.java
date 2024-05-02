package com.skillstorm.taxprep.server.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_address")
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @Column(name = "user_id")
  private int userId;

  @Column(name = "street_1")
  private String street1;

  @Column(name = "street_2")
  private String street2;

  @Column
  private String city;

  @Column
  private String state;

  @Column(name = "postal_code")
  private String postalCode;

  public Address() { }

  /* public Address(int id, Set<AppUser> user, String street1, String street2, String city, String state,
      String postalCode) {
    this.id = id;
    this.user = user;
    this.street1 = street1;
    this.street2 = street2;
    this.city = city;
    this.state = state;
    this.postalCode = postalCode;
  } */

  private Address(AddressBuilder builder) {
    this.userId = builder.userId;
    this.street1 = builder.street1;
    this.street2 = builder.street2;
    this.city = builder.city;
    this.state = builder.state;
    this.postalCode = builder.postalCode;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getStreet1() {
    return street1;
  }

  public void setStreet1(String street1) {
    this.street1 = street1;
  }

  public String getStreet2() {
    return street2;
  }

  public void setStreet2(String street2) {
    this.street2 = street2;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public static class AddressBuilder {
    private int userId;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String postalCode;

    public AddressBuilder() {
        // Initialize any default values if needed
    }

    public AddressBuilder userId(int userId) {
      this.userId = userId;
      return this;
    }

    public AddressBuilder street1(String street1) {
        this.street1 = street1;
        return this;
    }

    public AddressBuilder street2(String street2) {
        this.street2 = street2;
        return this;
    }

    public AddressBuilder city(String city) {
        this.city = city;
        return this;
    }

    public AddressBuilder state(String state) {
        this.state = state;
        return this;
    }

    public AddressBuilder postalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public Address build() {
        return new Address(this);
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    result = prime * result + ((street1 == null) ? 0 : street1.hashCode());
    result = prime * result + ((street2 == null) ? 0 : street2.hashCode());
    result = prime * result + ((city == null) ? 0 : city.hashCode());
    result = prime * result + ((state == null) ? 0 : state.hashCode());
    result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
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
    Address other = (Address) obj;
    if (id != other.id)
      return false;
    if (street1 == null) {
      if (other.street1 != null)
        return false;
    } else if (!street1.equals(other.street1))
      return false;
    if (street2 == null) {
      if (other.street2 != null)
        return false;
    } else if (!street2.equals(other.street2))
      return false;
    if (city == null) {
      if (other.city != null)
        return false;
    } else if (!city.equals(other.city))
      return false;
    if (state == null) {
      if (other.state != null)
        return false;
    } else if (!state.equals(other.state))
      return false;
    if (postalCode == null) {
      if (other.postalCode != null)
        return false;
    } else if (!postalCode.equals(other.postalCode))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Address [id=" + id + ", street1=" + street1 + ", street2=" + street2 + ", city="
        + city + ", state=" + state + ", postalCode=" + postalCode + "]";
  }
}
