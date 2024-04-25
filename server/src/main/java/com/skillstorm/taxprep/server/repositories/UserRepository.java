package com.skillstorm.taxprep.server.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.taxprep.server.models.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
  public Optional<AppUser> findByUsername(String username);
}
