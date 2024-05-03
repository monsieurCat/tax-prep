package com.skillstorm.taxprep.server.controllers;

import java.security.Principal;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.skillstorm.taxprep.server.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping("/home")
  public ResponseEntity<?> home(Principal principal) {
    if (principal != null) {
      UserDetails userDetails = userService.loadUserByUsername(principal.getName());
      return ResponseEntity.ok().body(Collections.singletonMap("username", userDetails.getUsername()));
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "User is not authenticated"));
    }
  }

}