package me.dserrano.inditex.prices.infrastructure.outbound.h2.repository;

import me.dserrano.inditex.prices.infrastructure.outbound.h2.model.PriceEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static me.dserrano.inditex.prices.infrastructure.outbound.h2.model.PriceEntityMother.*;

@DataR2dbcTest
public class PriceEntityRepositoryIT {

    @Autowired
    private PriceEntityRepository priceEntityRepository;

    @Test
    @DisplayName("Petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void test1() {
        // Given
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        String productId = "35455";
        String brandId = "1";

        // When
        Flux<PriceEntity> result = priceEntityRepository.getPricesBy(date, productId, brandId);

        // Then
        StepVerifier.create(result)
                .expectNext(PRICE_ENTITY_1)
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void test2() {
        // Given
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        String productId = "35455";
        String brandId = "1";

        // When
        Flux<PriceEntity> result = priceEntityRepository.getPricesBy(date, productId, brandId);

        // Then
        StepVerifier.create(result)
                .expectNext(PRICE_ENTITY_1)
                .expectNext(PRICE_ENTITY_2)
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void test3() {
        // Given
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        String productId = "35455";
        String brandId = "1";

        // When
        Flux<PriceEntity> result = priceEntityRepository.getPricesBy(date, productId, brandId);

        // Then
        StepVerifier.create(result)
                .expectNext(PRICE_ENTITY_1)
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)")
    void test4() {
        // Given
        LocalDateTime date = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
        String productId = "35455";
        String brandId = "1";

        // When
        Flux<PriceEntity> result = priceEntityRepository.getPricesBy(date, productId, brandId);

        // Then
        StepVerifier.create(result)
                .expectNext(PRICE_ENTITY_1)
                .expectNext(PRICE_ENTITY_3)
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)")
    void test5() {
        // Given
        LocalDateTime date = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        String productId = "35455";
        String brandId = "1";

        // When
        Flux<PriceEntity> result = priceEntityRepository.getPricesBy(date, productId, brandId);

        // Then
        StepVerifier.create(result)
                .expectNext(PRICE_ENTITY_1)
                .expectNext(PRICE_ENTITY_4)
                .expectComplete()
                .verify();
    }
}
