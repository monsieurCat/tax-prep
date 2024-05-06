package com.skillstorm.taxprep.server.utilities.mappers;

import com.skillstorm.taxprep.server.dtos.AddressDTO;
import com.skillstorm.taxprep.server.dtos.AppUserDTO;
import com.skillstorm.taxprep.server.dtos.UserInfoDTO;

public class UserInfoMapper {

    public AppUserDTO mapToAppUserDTO(UserInfoDTO userInfoDTO) {

        AppUserDTO appUserDTO = new AppUserDTO();

        appUserDTO.setFirstName(userInfoDTO.getFirstName());
        appUserDTO.setMiddleName(userInfoDTO.getMiddleName());
        appUserDTO.setLastName(userInfoDTO.getLastName());
        appUserDTO.setEmail(userInfoDTO.getEmail());
        appUserDTO.setUsername(userInfoDTO.getUsername());
        appUserDTO.setBirthday(userInfoDTO.getBirthday());
        appUserDTO.setRole(userInfoDTO.getRole());

        return appUserDTO;
    }

    public AddressDTO mapToAddressDTO(UserInfoDTO userInfoDTO) {

        AddressDTO addressDTO = new AddressDTO();
        
        addressDTO.setStreet1(userInfoDTO.getStreet1());
        addressDTO.setStreet2(userInfoDTO.getStreet2());
        addressDTO.setCity(userInfoDTO.getCity());
        addressDTO.setState(userInfoDTO.getState());
        addressDTO.setPostalCode(userInfoDTO.getPostalCode());

        return addressDTO;
    }
}
