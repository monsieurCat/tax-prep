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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin("*")
public class UserController {

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
  public ResponseEntity<?> updateUser(Principal principal, @Validated @RequestBody AppUserDTO user) {
    System.out.println("Received update request with data: " + user); // Log the received data
    try {
      if (principal != null) {
        
        // Get user's username
        String currentUsername = principal.getName();
        String newUsername = user.getUsername();

        // First update the user's info
        AppUserDTO updatedUser = userService.updateUser(currentUsername, user);
        
        // If the user is trying to change their username, update Spring Security's authentication
        if (!currentUsername.equals(newUsername)) {

          // First get the authenticatin from the Security Context Holder
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

          // If the authentication is valid
          if (authentication != null && authentication.isAuthenticated()) {

            // get the user's details
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            if (userDetails.getUsername().equals(currentUsername)) {

              // Get the updated user's full record
              UserDetails updatedUserDetails = userService.loadUserByUsername(newUsername);

              // Now update the authentication token for Spring Security
              Authentication updatedAuthentication = new UsernamePasswordAuthenticationToken(
                      updatedUserDetails, 
                      authentication.getCredentials(), 
                      authentication.getAuthorities());

              // And set the token back into the Security Context Holder for safe keeping :)
              SecurityContextHolder.getContext().setAuthentication(updatedAuthentication);
            }
          }
        }

        return ResponseEntity.ok().body(updatedUser);
      } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "User is not authenticated"));
      }
    } catch (UsernameAlreadyExistsException | UsernameNotFoundException | NotFoundException e) {
      System.out.println("Error from username: " + e.getMessage()); // Log specific exceptions
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
    } catch (Exception e) {
      System.out.println("Error from general exception: " + e.getMessage()); // Log unexpected exceptions
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
    }
  }

}