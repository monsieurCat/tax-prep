package com.skillstorm.taxprep.server.dtos;

import java.time.LocalDate;

public class AppUserDTO {

  private String firstName;
  private String middleName;
  private String lastName;
  private String email;
  private String ssn;
  private String username;
  private LocalDate birthday;
  private String role;


  public String getFirstName() {
      return firstName;
  }

  public void setFirstName(String firstName) {
      this.firstName = firstName;
  }

  public String getMiddleName() {
      return middleName;
  }

  public void setMiddleName(String middleName) {
      this.middleName = middleName;
  }

  public String getLastName() {
      return lastName;
  }

  public void setLastName(String lastName) {
      this.lastName = lastName;
  }

  public String getEmail() {
      return email;
  }

  public void setEmail(String email) {
      this.email = email;
  }

  public String getSsn() {
    return ssn;
  }

  public void setSsn(String ssn) {
    this.ssn = ssn;
  }

  public String getUsername() {
      return username;
  }

  public void setUsername(String username) {
      this.username = username;
  }

  public LocalDate getBirthday() {
      return birthday;
  }

  public void setBirthday(LocalDate birthday) {
      this.birthday = birthday;
  }

  public String getRole() {
      return role;
  }

  public void setRole(String role) {
      this.role = role;
  }
}
