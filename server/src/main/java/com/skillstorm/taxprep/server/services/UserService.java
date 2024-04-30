package com.skillstorm.taxprep.server.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skillstorm.taxprep.server.exceptions.UsernameAlreadyExistsException;
import com.skillstorm.taxprep.server.models.AppUser;
import com.skillstorm.taxprep.server.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
  
  @Autowired
  UserRepository userRepository;

  /**
   * search through the db for a user with username, or throw an exception if it can't find it
   * 
   * spring security will use this method to find users
   */

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDetails user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found."));



    return user;
  }

  public AppUser register(AppUser user) {
    Optional<AppUser> foundUser = userRepository.findByUsername(user.getUsername());
    if (foundUser.isPresent()) {
      throw new UsernameAlreadyExistsException(user.getUsername());
    }

    user.setRole("USER");

    return userRepository.save(user);
  }

  public void registerAdmin(AppUser user) {

    // first we need to check if the username is taken
    Optional<AppUser> foundUser = userRepository.findByUsername(user.getUsername());
    if(foundUser.isPresent()) {
        
        // [insert some logic to tell fronted that the username already exists]

        throw new RuntimeException("User with that username already exists.");
    }

    // setting the incoming user to the default level of access
    user.setRole("ADMIN");

    // finally save to db
    userRepository.save(user);
  }

  @PreAuthorize("hasAuthority('ROLE_ADMIN')")     // security advice - works like @Before 
  public long countAllUsers() {
      return userRepository.count();
  }
}

