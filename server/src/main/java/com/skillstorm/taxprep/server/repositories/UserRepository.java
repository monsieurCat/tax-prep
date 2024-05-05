package com.skillstorm.taxprep.server.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skillstorm.taxprep.server.models.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {
  public Optional<AppUser> findByUsername(String username);

  @Query("SELECT u.id FROM AppUser u WHERE u.username = :username")
  Optional<Integer> findUserIdByUsername(@Param("username") String username);
}
