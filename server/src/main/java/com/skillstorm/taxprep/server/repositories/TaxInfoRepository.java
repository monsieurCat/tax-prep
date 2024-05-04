package com.skillstorm.taxprep.server.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skillstorm.taxprep.server.models.TaxInfo;

@Repository
public interface TaxInfoRepository extends JpaRepository<TaxInfo, Integer> {
  public Optional<TaxInfo> findByUser_Id(int userId);

  @Query("SELECT t.id FROM TaxInfo t WHERE t.user.id = :userId")
  Optional<Integer> findTaxInfoIdByUserId(@Param("userId") int userId);
}
