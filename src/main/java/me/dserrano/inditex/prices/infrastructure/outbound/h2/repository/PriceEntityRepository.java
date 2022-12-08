package me.dserrano.inditex.prices.infrastructure.outbound.h2.repository;

import me.dserrano.inditex.prices.infrastructure.outbound.h2.model.PriceEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
public interface PriceEntityRepository extends ReactiveCrudRepository<PriceEntity, Long> {

    @Query("SELECT * FROM PRICES WHERE PRODUCT_ID = :productId AND BRAND_ID = :brandId AND :date BETWEEN START_DATE AND END_DATE")
    Flux<PriceEntity> getPricesBy(@Param("date") LocalDateTime date, @Param("productId") String productId, @Param("brandId") String brandId);

}
