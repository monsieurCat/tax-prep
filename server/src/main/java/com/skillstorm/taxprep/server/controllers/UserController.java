package com.skillstorm.taxprep.server.controllers;

import java.security.Principal;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.taxprep.server.dtos.AppUserDTO;
import com.skillstorm.taxprep.server.dtos.ChangePasswordDTO;
import com.skillstorm.taxprep.server.exceptions.NotFoundException;
import com.skillstorm.taxprep.server.exceptions.UsernameAlreadyExistsException;
import com.skillstorm.taxprep.server.models.AppUser;
import com.skillstorm.taxprep.server.services.UserService;
import com.skillstorm.taxprep.server.utilities.mappers.AppUserMapper;

@RestController
@RequestMapping("/user")
public class UserController {

  /* @Autowired
  AuthenticationManager authenticationManager; */

  @Autowired
  UserService userService;
  

  @GetMapping("/username")
  public ResponseEntity<?> findUsername(Principal principal) {
    if (principal != null) {
      UserDetails userDetails = userService.loadUserByUsername(principal.getName());
      return ResponseEntity.ok().body(Collections.singletonMap("username", userDetails.getUsername()));
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "User is not authenticated"));
    }
  }

  @GetMapping("/info")
  public ResponseEntity<?> findUserInfo(Principal principal) {
    if (principal != null) {
      AppUser user = userService.loadUserByUsername(principal.getName());
      return ResponseEntity.ok().body(AppUserMapper.mapToDTO(user));
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "User is not authenticated"));
    }
  }

  @PutMapping("/update")
  public ResponseEntity<?> updateUser(Principal principal, @RequestBody AppUserDTO user) {
    try {
      if (principal != null) {
        
        // Get user's username
        String currentUsername = principal.getName();
        String newUsername = user.getUsername();

        // First update the user's info
        AppUserDTO updatedUser = userService.updateUser(currentUsername, user);
        
        // If the user is trying to change their username, update Spring Security's authentication
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

        return ResponseEntity.ok().body(updatedUser);
      } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "User is not authenticated"));
      }
    } catch (UsernameAlreadyExistsException | UsernameNotFoundException | NotFoundException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
    }
  }

  /* @PutMapping("/updatePassword")
  public ResponseEntity<?> updatePassword (Principal principal, @RequestBody AppUser user) {
    if (principal != null) {
      AppUser user = userService.loadUserByUsername(principal.getName());
      return ResponseEntity.ok().body(user);
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "User is not authenticated"));
    }
  } */

}