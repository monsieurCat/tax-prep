package com.skillstorm.taxprep.server.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.skillstorm.taxprep.server.models.Address;
import com.skillstorm.taxprep.server.models.AppUser;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class AddressRepositoryTest {

  @Autowired
  private AddressRepository addressRepository;

  @Autowired
  private UserRepository userRepository;

  @Test
  public void testFindByUserId() {

    // Save the user and get the generated user ID
    AppUser user = new AppUser();
    user.setUsername("test");
    user.setPassword("test");
    user.setRole("ROLE_USER");
    user = userRepository.save(user);
    int userId = user.getId(); // Get the generated user ID

    // Create the address using the generated user ID
    Address address = new Address.AddressBuilder()
            .userId(userId) // Use the generated user ID
            .street1("123 Testing Blvd.")
            .city("Irvine")
            .state("CA")
            .postalCode("12345")
            .build();


    Address savedAddress = addressRepository.save(address);

    Optional<Address> foundAddressOptional = addressRepository.findByUserId(userId);

    assertTrue(foundAddressOptional.isPresent());
    Address foundAddress = foundAddressOptional.get();
    assertEquals(1, foundAddress.getId());
    assertEquals("123 Testing Blvd.", foundAddress.getStreet1());
    assertEquals("Irvine", foundAddress.getCity());
    assertEquals("CA", foundAddress.getState());
    assertEquals("12345", foundAddress.getPostalCode());
  }
}
