package com.skillstorm.taxprep.server.controllers;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.taxprep.server.dtos.AddressDTO;
import com.skillstorm.taxprep.server.exceptions.NotFoundException;
import com.skillstorm.taxprep.server.models.Address;
import com.skillstorm.taxprep.server.services.AddressService;
import com.skillstorm.taxprep.server.services.UserService;
import com.skillstorm.taxprep.server.utilities.mappers.AddressMapper;


@RestController
@RequestMapping("/address")
public class AddressController {

  @Autowired
  AddressService addressService;

  @Autowired
  UserService userService;

  @GetMapping()
  public ResponseEntity<?> findAddress(Principal principal) {
    try {
      int userId = userService.findUserIdByUsername(principal.getName());
      Address address = addressService.findByUserId(userId);

      AddressDTO addressDTO = AddressMapper.mapToDTO(address);

      return new ResponseEntity<AddressDTO>(addressDTO, HttpStatus.OK);
    } catch (NotFoundException | UsernameNotFoundException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
    }
  }

  @PutMapping()
  public ResponseEntity<?> updateAddress(Principal principal, @RequestBody AddressDTO addressDTO) {
    try {
      int userId = userService.findUserIdByUsername(principal.getName());
      Address address = addressService.findByUserId(userId);
      
      AddressMapper.updateEntity(address, addressDTO);

      addressService.saveAddress(address);

      return new ResponseEntity<AddressDTO>(AddressMapper.mapToDTO(address), HttpStatus.OK);
    } catch (NotFoundException | UsernameNotFoundException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", e.getMessage()));
    }
  }
  
  @GetMapping("/{addressId}")
  public ResponseEntity<?> findAddressById(@PathVariable int addressId) {
    Address address = addressService.findByAddressId(addressId);

    return new ResponseEntity<Address>(address, HttpStatus.OK);
  }

  @PostMapping()
  public ResponseEntity<?> createAddress(@RequestBody Address address) {
    Address createdAddress = addressService.saveAddress(address);

    return new ResponseEntity<Address>(createdAddress, HttpStatus.OK);
  }

  @DeleteMapping()
  public ResponseEntity<?> deleteAddress(@RequestBody Address address) {
    addressService.deleteAddress(address);
    return ResponseEntity.noContent().build();
  }
}
