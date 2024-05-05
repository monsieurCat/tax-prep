package com.skillstorm.taxprep.server.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.skillstorm.taxprep.server.models.Address;
import com.skillstorm.taxprep.server.services.AddressService;

public class AddressControllerTest {

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AddressController addressController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAddressById() {
        // Initialize mock data
        int addressId = 1;
        Address mockAddress = new Address();
        mockAddress.setId(addressId);
        when(addressService.findByAddressId(addressId)).thenReturn(mockAddress);

        // Invoke controller method
        ResponseEntity<?> responseEntity = addressController.findAddressById(addressId);

        // Verify service method invocation
        verify(addressService).findByAddressId(addressId);

        // Assert response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockAddress, responseEntity.getBody());
    }

    @Test
    public void testCreateAddress() {
        // Mock data
        Address address = new Address();
        Address createdAddress = new Address();
        when(addressService.saveAddress(address)).thenReturn(createdAddress);

        // Invoke controller method
        //ResponseEntity<?> responseEntity = addressController.createAddress(address);

        // Verify service method invocation
        verify(addressService).saveAddress(address);

        // Assert response
        //assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        //assertEquals(createdAddress, responseEntity.getBody());
    }

    @Test
    public void testUpdateAddress() {
        // Mock data
        Address address = new Address();
        Address updatedAddress = new Address();
        when(addressService.saveAddress(address)).thenReturn(updatedAddress);

        // Invoke controller method
        //ResponseEntity<?> responseEntity = addressController.updateAddress(address);

        // Verify service method invocation
        verify(addressService).saveAddress(address);

        // Assert response
        //assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        //assertEquals(updatedAddress, responseEntity.getBody());
    }

    @Test
    public void testDeleteAddress() {
        // Mock data
        Address address = new Address();

        // Invoke controller method
        ResponseEntity<?> responseEntity = addressController.deleteAddress(address);

        // Verify service method invocation
        verify(addressService).deleteAddress(address);

        // Assert response
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}
