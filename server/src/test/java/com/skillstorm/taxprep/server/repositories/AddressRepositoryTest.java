package com.skillstorm.taxprep.server.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.skillstorm.taxprep.server.models.Address;
import com.skillstorm.taxprep.server.models.AppUser;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureDataJpa
public class AddressRepositoryTest {

  @Autowired
  private AddressRepository addressRepository;

  @Test
  public void testFindByUserId() {

    int userId = 1;

    AppUser user = new AppUser();
    user.setId(userId);

    Address address = new Address.AddressBuilder()
            .street1("123 Testing Blvd.")
            .city("Irvine")
            .state("CA")
            .postalCode("12345")
            .build();


    addressRepository.save(address);

    Optional<Address> foundAddressOptional = addressRepository.findByUser_Id(userId);

    assertTrue(foundAddressOptional.isPresent());
    Address foundAddress = foundAddressOptional.get();
    assertEquals(1, foundAddress.getId());
    assertEquals("123 Testing Blvd.", foundAddress.getStreet1());
    assertEquals("Irvine", foundAddress.getCity());
    assertEquals("CA", foundAddress.getState());
    assertEquals("12345", foundAddress.getPostalCode());
  }
}
