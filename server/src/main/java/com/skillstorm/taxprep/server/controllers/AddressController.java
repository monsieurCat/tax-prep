package com.skillstorm.taxprep.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.taxprep.server.models.Address;
import com.skillstorm.taxprep.server.services.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

  @Autowired
  AddressService addressService;
  
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

  @PutMapping()
  public ResponseEntity<?> updateAddress(@RequestBody Address address) {
    Address updatedAddress = addressService.saveAddress(address);

    return new ResponseEntity<Address>(updatedAddress, HttpStatus.OK);
  }

  @DeleteMapping()
  public ResponseEntity<?> deleteAddress(@RequestBody Address address) {
    addressService.deleteAddress(address);
    return ResponseEntity.noContent().build();
  }
}
