package com.skillstorm.taxprep.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import com.skillstorm.taxprep.server.exceptions.NotFoundException;
import com.skillstorm.taxprep.server.models.Address;
import com.skillstorm.taxprep.server.repositories.AddressRepository;

@Service
public class AddressService {
  
  @Autowired
  private AddressRepository addressRepository;

  public List<Address> findAllAddresses() {
    return addressRepository.findAll();
  }

  public Address findByAddressId(int id) {
    Optional<Address> address = addressRepository.findById(id);

    if (address.isPresent()) {
      return address.get();
    }

    return null;
  }

  public Address findByUserId(int id) {
    Optional<Address> address = addressRepository.findByUserId(id);

    if (address.isPresent()) {
      return address.get();
    } else {
      throw new NotFoundException("No address found");
    }
  }

  public Address saveAddress(Address address) {
    return addressRepository.save(address);
  }

  public void deleteAddress(Address address) {
    addressRepository.delete(address);
  }
}
