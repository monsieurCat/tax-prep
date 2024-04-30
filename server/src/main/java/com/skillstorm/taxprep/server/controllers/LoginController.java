package com.skillstorm.taxprep.server.controllers;

import java.security.Principal;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.taxprep.server.exceptions.UsernameAlreadyExistsException;
import com.skillstorm.taxprep.server.models.AppUser;
import com.skillstorm.taxprep.server.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class LoginController {

  @Autowired
  private OAuth2AuthorizedClientService authorizedClientService;

  @Autowired
  private UserService userService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @GetMapping("/login")
  public String login(@RequestParam(name = "error", required = false) String error) {
    if (error != null) {
      return error;
    }
    return "login";
  }

  /* @PostMapping("/login")
  public ResponseEntity<String> login(HttpServletRequest request, @RequestBody AppUser user){
    try {
      Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          user.getUsername(),
          user.getPassword()
        )
      );

      SecurityContext securityContext = SecurityContextHolder.getContext();
      securityContext.setAuthentication(authentication);

      // Create or retrieve the HttpSession
      HttpSession session = request.getSession(true);
      session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);

      return new ResponseEntity<>("Successfully logged in", HttpStatus.OK);
    } catch (AuthenticationException e) {
      return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
    }
  } */

  @GetMapping("/register")
  public String register() {
    return "register";
  }

  @GetMapping("/privateData")
  public String privateData() {
    return "private";
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody AppUser user) {
    try {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      AppUser registeredUser = userService.register(user);
      return new ResponseEntity<AppUser>(registeredUser, HttpStatus.OK);
    } catch (UsernameAlreadyExistsException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
    }
  }
  
}
