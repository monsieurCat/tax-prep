package com.skillstorm.taxprep.server.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTest {

  private Address.AddressBuilder addressBuilder;

  @BeforeEach
  public void setUp() {
    addressBuilder = new Address.AddressBuilder();
  }

  @Test
  public void testBuilder() {
      Address address = addressBuilder
              .street1("123 Testing St")
              .city("Irvine")
              .state("CA")
              .postalCode("12345")
              .build();

      assertNotNull(address);
      assertEquals("123 Testing St", address.getStreet1());
      assertEquals("Irvine", address.getCity());
      assertEquals("CA", address.getState());
      assertEquals("12345", address.getPostalCode());
  }

  @Test
  public void testGettersAndSetters() {
      Address address = new Address();

      address.setId(1);
      address.setStreet1("123 Testing St");
      address.setCity("Irvine");
      address.setState("CA");
      address.setPostalCode("12345");

      assertEquals(1, address.getId());
      assertEquals("123 Testing St", address.getStreet1());
      assertEquals("Irvine", address.getCity());
      assertEquals("CA", address.getState());
      assertEquals("12345", address.getPostalCode());
  }

  @Test
  public void testEqualsAndHashCode() {
      Address address1 = addressBuilder
              .street1("123 Testing St")
              .city("Irvine")
              .state("CA")
              .postalCode("12345")
              .build();

      Address address2 = addressBuilder
              .street1("123 Testing St")
              .city("Irvine")
              .state("CA")
              .postalCode("12345")
              .build();

      assertEquals(address1, address2);
      assertEquals(address1.hashCode(), address2.hashCode());

      address2.setStreet1("123 Test Blvd");

      assertNotEquals(address1, address2);
      assertNotEquals(address1.hashCode(), address2.hashCode());
  }

  @Test
  public void testToString() {
      Address address = addressBuilder
              .street1("123 Testing St")
              .city("Irvine")
              .state("CA")
              .postalCode("12345")
              .build();

      String expectedToString = "Address [id=0, street1=123 Testing St, street2=null, city=Irvine, state=CA, postalCode=12345]";
      assertEquals(expectedToString, address.toString());
  }
}