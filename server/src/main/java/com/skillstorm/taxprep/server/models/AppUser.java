package com.skillstorm.taxprep.server.models;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class AppUser implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private long id;

  @Column
  private String first_name;

  @Column
  private String last_name;

  @Column
  private String username;

  @Column
  private String password;

  @Column
  private String email;

  @Column
  private LocalDate birthday;

  @Column(name = "user_role")
  private String role;              // USER, ADMIN, MOD, etc. that we define
  
  public AppUser() {
  }


  public AppUser(long id, String first_name, String last_name, String username, String password, String email,
      LocalDate birthday, String role) {
    this.id = id;
    this.first_name = first_name;
    this.last_name = last_name;
    this.username = username;
    this.password = password;
    this.email = email;
    this.birthday = birthday;
    this.role = role;
  }

  public AppUser(String first_name, String last_name, String username, String password, String email,
      LocalDate birthday, String role) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.username = username;
    this.password = password;
    this.email = email;
    this.birthday = birthday;
    this.role = role;
  }

  public long getId() {
    return id;
  }


  public void setId(long id) {
    this.id = id;
  }


  public String getFirst_name() {
    return first_name;
  }


  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }


  public String getLast_name() {
    return last_name;
  }


  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }


  public String getUsername() {
    return username;
  }


  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }


  public void setPassword(String password) {
    this.password = password;
  }


  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
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

  

  // methods to use to manage users regarding security
  // if any of them return false, the user will not be able to log in

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + ((first_name == null) ? 0 : first_name.hashCode());
    result = prime * result + ((last_name == null) ? 0 : last_name.hashCode());
    result = prime * result + ((username == null) ? 0 : username.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
    result = prime * result + ((role == null) ? 0 : role.hashCode());
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
    AppUser other = (AppUser) obj;
    if (id != other.id)
      return false;
    if (first_name == null) {
      if (other.first_name != null)
        return false;
    } else if (!first_name.equals(other.first_name))
      return false;
    if (last_name == null) {
      if (other.last_name != null)
        return false;
    } else if (!last_name.equals(other.last_name))
      return false;
    if (username == null) {
      if (other.username != null)
        return false;
    } else if (!username.equals(other.username))
      return false;
    if (password == null) {
      if (other.password != null)
        return false;
    } else if (!password.equals(other.password))
      return false;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (birthday == null) {
      if (other.birthday != null)
        return false;
    } else if (!birthday.equals(other.birthday))
      return false;
    if (role == null) {
      if (other.role != null)
        return false;
    } else if (!role.equals(other.role))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "AppUser [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", username=" + username
        + ", password=" + password + ", email=" + email + ", birthday=" + birthday + ", role=" + role + "]";
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    
    /**
     * returns a collection of any objects that extend the GrantedAuthority class
     *  we will use SimpleGrantedAuthority to indicate the user's role
     * 
     * in spring security, any text can be recognized as an authority
     *  but roles specifically need to be in the format of "ROLE_<role_name>"
     *  SimpleGrantedAuthority converts user defined role into this accepted format
     */
    
    Set<SimpleGrantedAuthority> authorities = new HashSet<>();
    SimpleGrantedAuthority userRole = new SimpleGrantedAuthority(role);
    authorities.add(userRole);

    return authorities;

    //throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
    //throw new UnsupportedOperationException("Unimplemented method 'isAccountNonExpired'");
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
    //throw new UnsupportedOperationException("Unimplemented method 'isAccountNonLocked'");
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
    //throw new UnsupportedOperationException("Unimplemented method 'isCredentialsNonExpired'");
  }

  @Override
  public boolean isEnabled() {
    return true;
    //throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
  }

  
}
