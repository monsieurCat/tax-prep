package com.skillstorm.taxprep.server.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.skillstorm.taxprep.server.dtos.AppUserDTO;
import com.skillstorm.taxprep.server.exceptions.IncorrectPasswordException;
import com.skillstorm.taxprep.server.exceptions.NotFoundException;
import com.skillstorm.taxprep.server.exceptions.UsernameAlreadyExistsException;
import com.skillstorm.taxprep.server.models.AppUser;
import com.skillstorm.taxprep.server.repositories.UserRepository;
import com.skillstorm.taxprep.server.utilities.mappers.AppUserMapper;

@Service
public class UserService implements UserDetailsService {
  
  @Autowired
  UserRepository userRepository;

  @Override
  public AppUser loadUserByUsername(String username) throws UsernameNotFoundException {
    AppUser user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found."));

    return user;
  }

  public int findUserIdByUsername(String username) {
    Optional<Integer> userId = userRepository.findUserIdByUsername(username);

    if (userId.isPresent()) {
      return userId.get();
    } else {
      throw new UsernameNotFoundException(username + " not found.");
    }
  }

  /* public AppUserDTO updateUsername(String currentUsername, String newUsername) {
    AppUser currentUser = loadUserByUsername(currentUsername);
    
    if (currentUsername != newUsername) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (authentication != null && authentication.isAuthenticated()) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (userDetails.getUsername().equals(currentUsername)) {
            UserDetails updatedUserDetails = userService.loadUserByUsername(newUsername);
            Authentication updatedAuthentication = new UsernamePasswordAuthenticationToken(
                    updatedUserDetails, 
                    authentication.getCredentials(), 
                    authentication.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(updatedAuthentication);
        }
      }
    }
  } */

  public AppUserDTO updateUser(String currentUsername, AppUserDTO user) {
    
    // Check if new username is already taken
    if (!currentUsername.equals(user.getUsername())) {
      Optional<AppUser> existingUser = userRepository.findByUsername(user.getUsername());

      if (existingUser.isPresent()) {
        throw new UsernameAlreadyExistsException(user.getUsername());
      }
    }

    // Get current user to update
    Optional<AppUser> foundUser = userRepository.findByUsername(currentUsername);

    if (foundUser.isPresent()) {
      AppUser currentUser = foundUser.get();
      currentUser = AppUserMapper.updateEntity(user, currentUser);
      userRepository.save(currentUser);
      return AppUserMapper.mapToDTO(currentUser);

    } else {
      throw new UsernameNotFoundException(user.getUsername() + " not found.");
    }
  }

  public AppUserDTO updateUserPassword(String username, String currentPassword, String newEncodedPassword) {

    // Get current user to update
    Optional<AppUser> foundUser = userRepository.findByUsername(username);

    if (foundUser.isPresent()) {
      AppUser currentUser = foundUser.get();

      if (currentPassword != currentUser.getPassword()) {
        throw new IncorrectPasswordException("Invalid Password");
      }

      currentUser.setPassword(newEncodedPassword);
      currentUser = userRepository.save(currentUser);
      return AppUserMapper.mapToDTO(currentUser);
    } else {
      throw new UsernameNotFoundException(username + " not found.");
    }
  }

  public AppUser register(AppUser user) {
    Optional<AppUser> foundUser = userRepository.findByUsername(user.getUsername());
    if (foundUser.isPresent()) {
      throw new UsernameAlreadyExistsException(user.getUsername());
    }

    user.setRole("USER");

    return userRepository.save(user);
  }

  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public void registerAdmin(AppUser user) {

    // first we need to check if the username is taken
    Optional<AppUser> foundUser = userRepository.findByUsername(user.getUsername());
    
    if(foundUser.isPresent()) {
        throw new RuntimeException("User with that username already exists.");
    }

    // setting the incoming user to the default level of access
    user.setRole("ADMIN");

    // finally save to db
    userRepository.save(user);
  }

  public void deleteUser(AppUser user) {
    userRepository.delete(user);
  }

  @PreAuthorize("hasAuthority('ROLE_ADMIN')")     // security advice - works like @Before 
  public long countAllUsers() {
      return userRepository.count();
  }
}

