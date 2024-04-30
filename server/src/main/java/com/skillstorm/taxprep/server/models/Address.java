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
@Table(name = "addresses")
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @OneToMany(targetEntity = AppUser.class, mappedBy = "address", cascade = CascadeType.REMOVE)
  @JsonIgnore
  private Set<AppUser> user;

  @Column
  private String street_1;

  @Column
  private String street_2;

  @Column
  private String city;

  @Column
  private String state;

  @Column
  private String postal_code;

  public Address(int id, Set<AppUser> user, String street_1, String street_2, String city, String state,
      String postal_code) {
    this.id = id;
    this.user = user;
    this.street_1 = street_1;
    this.street_2 = street_2;
    this.city = city;
    this.state = state;
    this.postal_code = postal_code;
  }

  public Address(Set<AppUser> user, String street_1, String street_2, String city, String state, String postal_code) {
    this.user = user;
    this.street_1 = street_1;
    this.street_2 = street_2;
    this.city = city;
    this.state = state;
    this.postal_code = postal_code;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Set<AppUser> getUser() {
    return user;
  }

  public void setUser(Set<AppUser> user) {
    this.user = user;
  }

  public String getStreet_1() {
    return street_1;
  }

  public void setStreet_1(String street_1) {
    this.street_1 = street_1;
  }

  public String getStreet_2() {
    return street_2;
  }

  public void setStreet_2(String street_2) {
    this.street_2 = street_2;
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

  public String getPostal_code() {
    return postal_code;
  }

  public void setPostal_code(String postal_code) {
    this.postal_code = postal_code;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    result = prime * result + ((user == null) ? 0 : user.hashCode());
    result = prime * result + ((street_1 == null) ? 0 : street_1.hashCode());
    result = prime * result + ((street_2 == null) ? 0 : street_2.hashCode());
    result = prime * result + ((city == null) ? 0 : city.hashCode());
    result = prime * result + ((state == null) ? 0 : state.hashCode());
    result = prime * result + ((postal_code == null) ? 0 : postal_code.hashCode());
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
    if (user == null) {
      if (other.user != null)
        return false;
    } else if (!user.equals(other.user))
      return false;
    if (street_1 == null) {
      if (other.street_1 != null)
        return false;
    } else if (!street_1.equals(other.street_1))
      return false;
    if (street_2 == null) {
      if (other.street_2 != null)
        return false;
    } else if (!street_2.equals(other.street_2))
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
    if (postal_code == null) {
      if (other.postal_code != null)
        return false;
    } else if (!postal_code.equals(other.postal_code))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Address [id=" + id + ", user=" + user + ", street_1=" + street_1 + ", street_2=" + street_2 + ", city="
        + city + ", state=" + state + ", postal_code=" + postal_code + "]";
  }
}
