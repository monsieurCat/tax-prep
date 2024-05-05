package com.skillstorm.taxprep.server.utilities.mappers;

import com.skillstorm.taxprep.server.dtos.AppUserDTO;
import com.skillstorm.taxprep.server.models.AppUser;

public class AppUserMapper {
  
  public static AppUserDTO mapToDTO(AppUser user) {
    if (user == null) {
        return null;
    }

    AppUserDTO dto = new AppUserDTO();
    dto.setFirstName(user.getFirstName());
    dto.setMiddleName(user.getMiddleName());
    dto.setLastName(user.getLastName());
    dto.setEmail(user.getEmail());
    dto.setUsername(user.getUsername());
    dto.setBirthday(user.getBirthday());
    dto.setRole(user.getRole());

    return dto;
  }

  public static AppUser mapToEntity(AppUserDTO dto, int userId, String password) {
    return new AppUser.AppUserBuilder()
      .id(userId)
      .firstName(dto.getFirstName())
      .middleName(dto.getMiddleName())
      .lastName(dto.getLastName())
      .email(dto.getEmail())
      .username(dto.getUsername())
      .password(password)
      .birthday(dto.getBirthday())
      .role(dto.getRole())
      .build();
}

  public static AppUser updateEntity(AppUserDTO dto, AppUser user) {
    user.setFirstName(dto.getFirstName());
    user.setMiddleName(dto.getMiddleName());
    user.setLastName(dto.getLastName());
    user.setEmail(dto.getEmail());
    user.setUsername(dto.getUsername());
    user.setBirthday(dto.getBirthday());
    user.setRole(dto.getRole());
    
    return user;
  }
}
