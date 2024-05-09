package com.skillstorm.taxprep.server.controllers;
import java.security.Principal;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.taxprep.server.dtos.AppUserDTO;
import com.skillstorm.taxprep.server.dtos.ChangePasswordDTO;
import com.skillstorm.taxprep.server.exceptions.IncorrectPasswordException;
import com.skillstorm.taxprep.server.exceptions.UsernameAlreadyExistsException;
import com.skillstorm.taxprep.server.models.Address;
import com.skillstorm.taxprep.server.models.AppUser;
import com.skillstorm.taxprep.server.models.Income1099;
import com.skillstorm.taxprep.server.models.IncomeW2;
import com.skillstorm.taxprep.server.models.TaxInfo;
import com.skillstorm.taxprep.server.services.AddressService;
import com.skillstorm.taxprep.server.services.Income1099Service;
import com.skillstorm.taxprep.server.services.IncomeW2Service;
import com.skillstorm.taxprep.server.services.TaxInfoService;
import com.skillstorm.taxprep.server.services.UserService;
import com.skillstorm.taxprep.server.utilities.mappers.AppUserMapper;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/auth")
public class LoginController {

  /* @Autowired
  AuthenticationManager authenticationManager; */
  
  @Autowired
  private UserService userService;

  @Autowired
  private AddressService addressService;

  @Autowired
  private TaxInfoService taxInfoService;

  @Autowired
  private IncomeW2Service incomeW2Service;

  @Autowired
  private Income1099Service income1099Service;

  @Autowired
  private PasswordEncoder passwordEncoder;
  

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody AppUser user) {
    try {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      AppUser registeredUser = userService.register(user);

      // Initialize an empty address record for the new user
      Address address = new Address.AddressBuilder().userId(registeredUser.getId()).build();
      address = addressService.saveAddress(address);

      // Initialize an empty tax info record for the new user
      TaxInfo taxInfo = new TaxInfo.TaxInfoBuilder().user(registeredUser).build();
      taxInfo = taxInfoService.saveTaxInfo(taxInfo);

      // Initialize an empty W2 income record for the new user
      IncomeW2 incomeW2 = new IncomeW2.Builder().taxInfoId(taxInfo.getId()).build();
      incomeW2 = incomeW2Service.saveOrUpdateIncome(incomeW2);

      // Initialize an empty 1099 income record the the new user
      Income1099 income1099 = new Income1099.Builder().taxInfoId(taxInfo.getId()).build();
      income1099 = income1099Service.saveOrUpdateIncome(income1099);

      return new ResponseEntity<AppUserDTO>(AppUserMapper.mapToDTO(registeredUser), HttpStatus.OK);
    } catch (UsernameAlreadyExistsException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
    }
  }

  @PreAuthorize("hasRole('USER')")
  @PostMapping("/change_password")
  public ResponseEntity<?> changePassword(Principal principal, @RequestBody ChangePasswordDTO changePasswordRequest) {
    try {
      // Retrieve the username of the currently authenticated user
      String username = principal.getName();

      // Authenticate the user with their current password
      /* UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
              username, changePasswordRequest.getCurrentPassword());
      authenticationManager.authenticate(authToken); */

      String currentPassword = changePasswordRequest.getCurrentPassword();
      String encodedCurrentPassword = passwordEncoder.encode(currentPassword);

      // If authentication succeeds, update the user's password
      String newPassword = changePasswordRequest.getNewPassword();
      String encodedNewPassword = passwordEncoder.encode(newPassword);
      userService.updateUserPassword(username, encodedCurrentPassword, encodedNewPassword);

      return ResponseEntity.status(HttpStatus.OK).body("Password changed successfully");
    } catch (UsernameNotFoundException | BadCredentialsException | IncorrectPasswordException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
    }
  }
  
}