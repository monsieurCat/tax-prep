package com.skillstorm.taxprep.server.utilities.mappers;

import com.skillstorm.taxprep.server.dtos.AddressDTO;
import com.skillstorm.taxprep.server.models.Address;

public class AddressMapper {

  public static AddressDTO mapToDTO(Address address) {
      AddressDTO dto = new AddressDTO();
      dto.setStreet1(address.getStreet1());
      dto.setStreet2(address.getStreet2());
      dto.setCity(address.getCity());
      dto.setState(address.getState());
      dto.setPostalCode(address.getPostalCode());

      return dto;
  }

  public static Address mapToEntity(AddressDTO addressDTO, int userId, int addressId) {
    Address address = new Address();
    address.setId(addressId);
    address.setUserId(userId);
    address.setStreet1(addressDTO.getStreet1());
    address.setStreet2(addressDTO.getStreet2());
    address.setCity(addressDTO.getCity());
    address.setState(addressDTO.getState());
    address.setPostalCode(addressDTO.getPostalCode());

    return address;
  }

  public static void updateEntity(Address addressToUpdate, AddressDTO addressDTO) {
    // Update the fields of the existing Address entity
    addressToUpdate.setStreet1(addressDTO.getStreet1());
    addressToUpdate.setStreet2(addressDTO.getStreet2());
    addressToUpdate.setCity(addressDTO.getCity());
    addressToUpdate.setState(addressDTO.getState());
    addressToUpdate.setPostalCode(addressDTO.getPostalCode());
  }
}
