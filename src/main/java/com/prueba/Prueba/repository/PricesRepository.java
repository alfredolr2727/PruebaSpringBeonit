package com.prueba.Prueba.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PricesRepository extends JpaRepository<Price, Integer> {

        @Query(value = "SELECT * FROM PRICES WHERE ?1 BETWEEN startDate AND endDate AND productId = ?2 AND brandId = ?3",
            nativeQuery = true)
        List<Price> findByParameters(final LocalDateTime applicationDate, final Long productId, final Integer brandId);


}