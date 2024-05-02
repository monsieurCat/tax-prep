package com.skillstorm.taxprep.server.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.skillstorm.taxprep.server.models.Address;
import com.skillstorm.taxprep.server.repositories.AddressRepository;

@SpringBootTest
public class AddressServiceTest {
  
  @Autowired
  private AddressService addressService;

  @MockBean
  private AddressRepository addressRepository;

  @Test
  public void testFindAddressByUserId() {
    
    int userId = 1;
    int addressId = 2;

    Address mockAddress = new Address.AddressBuilder()
                                    .street1("123 Test St.")
                                    .city("Cupertino")
                                    .state("CA")
                                    .postalCode("12345")
                                    .build();
    mockAddress.setId(addressId);
    
    Mockito.when(addressRepository.findByUserId(userId)).thenReturn(Optional.of(mockAddress));

    Address result = addressService.findByUserId(userId);

    assertNotNull(result);
    assertEquals(addressId, result.getId());
    assertEquals("Cupertino", result.getCity());
    assertEquals("CA", result.getState());
    assertEquals("123 Test St.", result.getStreet1());
    assertEquals(mockAddress, result);
  }
}
