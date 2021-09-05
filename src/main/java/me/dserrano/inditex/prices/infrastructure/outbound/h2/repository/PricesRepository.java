package me.dserrano.inditex.prices.infrastructure.outbound.h2.repository;

import me.dserrano.inditex.prices.infrastructure.outbound.h2.model.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PricesRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p WHERE PRODUCT_ID = :productId AND BRAND_ID = :brandId AND :date BETWEEN START_DATE AND END_DATE")
    List<PriceEntity> getPricesBy(@Param("date") LocalDateTime date, @Param("productId") String productId, @Param("brandId") String brandId);

}
