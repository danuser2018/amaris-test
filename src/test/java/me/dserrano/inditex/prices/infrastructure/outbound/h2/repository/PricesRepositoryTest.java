package me.dserrano.inditex.prices.infrastructure.outbound.h2.repository;

import me.dserrano.inditex.prices.infrastructure.outbound.h2.model.PriceEntity;
import org.junit.jupiter.api.BeforeEach;
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
public class PricesRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PricesRepository pricesRepository;

    @BeforeEach
    private void setUp() {
        testEntityManager.clear();
        testEntityManager.persist(PRICE_ENTITY_1);
        testEntityManager.persist(PRICE_ENTITY_2);
        testEntityManager.persist(PRICE_ENTITY_3);
        testEntityManager.persist(PRICE_ENTITY_4);
        testEntityManager.flush();
    }

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

}
