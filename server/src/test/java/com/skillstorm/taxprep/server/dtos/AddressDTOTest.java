package com.skillstorm.taxprep.server.dtos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddressDTOTest {

    @Test
    void testGettersAndSetters() {
        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setStreet1("123 Main St");
        addressDTO.setStreet2("Apt 101");
        addressDTO.setCity("Anytown");
        addressDTO.setState("CA");
        addressDTO.setPostalCode("12345");

        assertEquals("123 Main St", addressDTO.getStreet1());
        assertEquals("Apt 101", addressDTO.getStreet2());
        assertEquals("Anytown", addressDTO.getCity());
        assertEquals("CA", addressDTO.getState());
        assertEquals("12345", addressDTO.getPostalCode());
    }

    @Test
    void testDefaultConstructor() {
        AddressDTO addressDTO = new AddressDTO();

        assertNull(addressDTO.getStreet1());
        assertNull(addressDTO.getStreet2());
        assertNull(addressDTO.getCity());
        assertNull(addressDTO.getState());
        assertNull(addressDTO.getPostalCode());
    }

    @Test
    void testSettersWithNullValues() {
        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setStreet1(null);
        addressDTO.setStreet2(null);
        addressDTO.setCity(null);
        addressDTO.setState(null);
        addressDTO.setPostalCode(null);

        assertNull(addressDTO.getStreet1());
        assertNull(addressDTO.getStreet2());
        assertNull(addressDTO.getCity());
        assertNull(addressDTO.getState());
        assertNull(addressDTO.getPostalCode());
    }
}
