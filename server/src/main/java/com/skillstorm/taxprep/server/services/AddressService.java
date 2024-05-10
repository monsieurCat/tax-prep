package com.skillstorm.taxprep.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.taxprep.server.exceptions.NotFoundException;
import com.skillstorm.taxprep.server.models.Address;
import com.skillstorm.taxprep.server.repositories.AddressRepository;

@Service
public class AddressService {
  
  @Autowired
  private AddressRepository addressRepository;

  // Find all addresses
  public List<Address> findAllAddresses() {
    return addressRepository.findAll();
  }

  // Find address by specific address id
  public Address findByAddressId(int id) {
    Optional<Address> address = addressRepository.findById(id);

    if (address.isPresent()) {
      return address.get();
    }

    return null;
  }

  // Find address by user id
  public Address findByUserId(int id) {
    Optional<Address> address = addressRepository.findByUserId(id);

    if (address.isPresent()) {
      return address.get();
    } else {
      throw new NotFoundException("No address found");
    }
  }

  // Save address to database
  public Address saveAddress(Address address) {
    return addressRepository.save(address);
  }

  // Delete address
  public void deleteAddress(Address address) {
    addressRepository.delete(address);
  }
}
