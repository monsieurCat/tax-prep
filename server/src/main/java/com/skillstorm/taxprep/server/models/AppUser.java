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
  private int id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "middle_name")
  private String middleName;

  @Column(name = "last_name")
  private String lastName;

  @Column
  private String email;

  @Column
  private String username;

  @Column
  private String password;

  @Column
  private LocalDate birthday;

  @Column(name = "user_role")
  private String role;
  
  public AppUser() {
  }

  public AppUser(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public static class AppUserBuilder {
    private AppUser user;

    public AppUserBuilder() {
        user = new AppUser();
    }

    public AppUserBuilder id(int id) {
        user.setId(id);
        return this;
    }

    public AppUserBuilder firstName(String firstName) {
        user.setFirstName(firstName);
        return this;
    }

    public AppUserBuilder middleName(String middleName) {
      user.setMiddleName(middleName);
      return this;
    }

    public AppUserBuilder lastName(String lastName) {
        user.setLastName(lastName);
        return this;
    }

    public AppUserBuilder username(String username) {
        user.setUsername(username);
        return this;
    }

    public AppUserBuilder password(String password) {
        user.setPassword(password);
        return this;
    }

    public AppUserBuilder email(String email) {
        user.setEmail(email);
        return this;
    }

    public AppUserBuilder birthday(LocalDate birthday) {
        user.setBirthday(birthday);
        return this;
    }

    public AppUserBuilder role(String role) {
        user.setRole(role);
        return this;
    }

    public AppUser build() {
        return user;
    }
  }

  /* public AppUser(int id, String firstName, String lastName, String username, String password, Address address, String email,
      LocalDate birthday, String role) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
    this.address = address;
    this.email = email;
    this.birthday = birthday;
    this.role = role;
  }

  public AppUser(String firstName, String lastName, String username, String password, Address address, String email,
      LocalDate birthday, String role) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
    this.address = address;
    this.email = email;
    this.birthday = birthday;
    this.role = role;
  } */

  public int getId() {
    return id;
  }


  public void setId(int id) {
    this.id = id;
  }


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


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((username == null) ? 0 : username.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
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
    if (firstName == null) {
      if (other.firstName != null)
        return false;
    } else if (!firstName.equals(other.firstName))
      return false;
    if (middleName == null) {
      if (other.middleName != null)
        return false;
    } else if (!middleName.equals(other.middleName))
      return false;
    if (lastName == null) {
      if (other.lastName != null)
        return false;
    } else if (!lastName.equals(other.lastName))
      return false;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
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
    return "AppUser [id=" + id + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
        + ", email=" + email + ", username=" + username + ", password=" + password + ", birthday=" + birthday
        + ", role=" + role + "]";
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
    String authority = "ROLE_" + role;
    SimpleGrantedAuthority userRole = new SimpleGrantedAuthority(authority);
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
