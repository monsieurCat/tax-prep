package com.skillstorm.taxprep.server.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.skillstorm.taxprep.server.exceptions.NotFoundException;
import com.skillstorm.taxprep.server.models.Address;
import com.skillstorm.taxprep.server.repositories.AddressRepository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllAddresses() {
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address.AddressBuilder().userId(1).street1("Street 1").city("City 1").state("State 1").postalCode("12345").build());
        addresses.add(new Address.AddressBuilder().userId(2).street1("Street 2").city("City 2").state("State 2").postalCode("54321").build());

        when(addressRepository.findAll()).thenReturn(addresses);

        List<Address> result = addressService.findAllAddresses();
        assertEquals(addresses.size(), result.size());
        assertEquals(addresses, result);
    }

    @Test
    public void testFindByAddressId_ExistingAddress() {
        Address address = new Address.AddressBuilder().userId(1).street1("Street 1").city("City 1").state("State 1").postalCode("12345").build();

        when(addressRepository.findById(1)).thenReturn(Optional.of(address));

        Address result = addressService.findByAddressId(1);
        assertNotNull(result);
        assertEquals(address, result);
    }

    @Test
    public void testFindByAddressId_NonExistingAddress() {
        when(addressRepository.findById(1)).thenReturn(Optional.empty());
        Address result = addressService.findByAddressId(1);
        assertNull(result);
    }

    @Test
    public void testFindByUserId_ExistingAddress() {        Address address = new Address.AddressBuilder().userId(1).street1("Street 1").city("City 1").state("State 1").postalCode("12345").build();
        when(addressRepository.findByUserId(1)).thenReturn(Optional.of(address));
        Address result = addressService.findByUserId(1);
        assertNotNull(result);
        assertEquals(address, result);
    }

    @Test
    public void testFindByUserId_NonExistingAddress() {
        when(addressRepository.findByUserId(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> addressService.findByUserId(1));
    }

    @Test
    public void testSaveAddress() {
        Address address = new Address.AddressBuilder().userId(1).street1("Street 1").city("City 1").state("State 1").postalCode("12345").build();

        when(addressRepository.save(address)).thenReturn(address);

        Address result = addressService.saveAddress(address);
        assertNotNull(result);
        assertEquals(address, result);
    }

    @Test
    public void testDeleteAddress() {
        Address address = new Address.AddressBuilder().userId(1).street1("Street 1").city("City 1").state("State 1").postalCode("12345").build();

        assertDoesNotThrow(() -> addressService.deleteAddress(address));
        verify(addressRepository, times(1)).delete(address);
    }
}
