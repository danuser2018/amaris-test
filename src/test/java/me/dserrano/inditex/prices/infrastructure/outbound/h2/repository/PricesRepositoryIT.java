package me.dserrano.inditex.prices.infrastructure.outbound.h2.repository;

import me.dserrano.inditex.prices.infrastructure.outbound.h2.model.PriceEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static me.dserrano.inditex.prices.infrastructure.outbound.h2.model.PriceEntityMother.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PricesRepositoryIT {

    @Autowired
    private PricesRepository pricesRepository;

    @Test
    @DisplayName("Petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void test1() {
        // Given
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        String productId = "35455";
        String brandId = "1";

        // When
        List<PriceEntity> result = pricesRepository.getPricesBy(date, productId, brandId);

        // Then
        assertEquals(1, result.size());
        assertEquals(PRICE_ENTITY_1, result.get(0));
    }

    @Test
    @DisplayName("Petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void test2() {
        // Given
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        String productId = "35455";
        String brandId = "1";

        // When
        List<PriceEntity> result = pricesRepository.getPricesBy(date, productId, brandId);

        // Then
        assertEquals(2, result.size());
        assertEquals(PRICE_ENTITY_1, result.get(0));
        assertEquals(PRICE_ENTITY_2, result.get(1));
    }

    @Test
    @DisplayName("Petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void test3() {
        // Given
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        String productId = "35455";
        String brandId = "1";

        // When
        List<PriceEntity> result = pricesRepository.getPricesBy(date, productId, brandId);

        // Then
        assertEquals(1, result.size());
        assertEquals(PRICE_ENTITY_1, result.get(0));
    }

    @Test
    @DisplayName("Petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)")
    void test4() {
        // Given
        LocalDateTime date = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
        String productId = "35455";
        String brandId = "1";

        // When
        List<PriceEntity> result = pricesRepository.getPricesBy(date, productId, brandId);

        // Then
        assertEquals(2, result.size());
        assertEquals(PRICE_ENTITY_1, result.get(0));
        assertEquals(PRICE_ENTITY_3, result.get(1));
    }

    @Test
    @DisplayName("Petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)")
    void test5() {
        // Given
        LocalDateTime date = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        String productId = "35455";
        String brandId = "1";

        // When
        List<PriceEntity> result = pricesRepository.getPricesBy(date, productId, brandId);

        // Then
        assertEquals(2, result.size());
        assertEquals(PRICE_ENTITY_1, result.get(0));
        assertEquals(PRICE_ENTITY_4, result.get(1));
    }
}
