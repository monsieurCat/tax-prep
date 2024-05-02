package com.skillstorm.taxprep.server.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.taxprep.server.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
  
  public Optional<Address> findByUserId(int userId);
}
