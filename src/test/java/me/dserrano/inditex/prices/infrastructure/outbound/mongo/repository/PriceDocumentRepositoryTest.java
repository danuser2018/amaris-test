package me.dserrano.inditex.prices.infrastructure.outbound.mongo.repository;

import me.dserrano.inditex.prices.infrastructure.outbound.mongo.model.PriceDocument;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.List;

import static me.dserrano.inditex.prices.infrastructure.outbound.mongo.model.PriceDocumentMother.*;

@Testcontainers
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@DirtiesContext
public class PriceDocumentRepositoryTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.3");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    ReactiveMongoTemplate mongoTemplate;

    @Autowired
    PriceDocumentRepository priceDocumentRepository;

    @BeforeEach
    void beforeEach() {
        Flux.fromIterable(
                List.of(PRICE_DOCUMENT_1, PRICE_DOCUMENT_2, PRICE_DOCUMENT_3, PRICE_DOCUMENT_4)
        ).subscribe(price -> mongoTemplate.save(price, "prices").block());
    }

    @Test
    @DisplayName("Petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void test1() {
        // Given
        var date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        var productId = "35455";
        var brandId = "1";

        // When
        var result = priceDocumentRepository.findPricesBy(date, productId, brandId);

        // Then
        StepVerifier.create(result)
                .expectNextMatches(p -> assertEqual(PRICE_DOCUMENT_1, p))
                .verifyComplete();
    }

    @Test
    @DisplayName("Petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void test2() {
        // Given
        var date = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        var productId = "35455";
        var brandId = "1";

        // When
        var result = priceDocumentRepository.findPricesBy(date, productId, brandId);

        // Then
        StepVerifier.create(result)
                .expectNextMatches(p -> assertEqual(PRICE_DOCUMENT_1, p))
                .expectNextMatches(p -> assertEqual(PRICE_DOCUMENT_2, p))
                .verifyComplete();
    }

    @Test
    @DisplayName("Petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void test3() {
        // Given
        var date = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        var productId = "35455";
        var brandId = "1";

        // When
        var result = priceDocumentRepository.findPricesBy(date, productId, brandId);

        // Then
        StepVerifier.create(result)
                .expectNextMatches(p -> assertEqual(PRICE_DOCUMENT_1, p))
                .verifyComplete();
    }

    @Test
    @DisplayName("Petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)")
    void test4() {
        // Given
        var date = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
        var productId = "35455";
        var brandId = "1";

        // When
        var result = priceDocumentRepository.findPricesBy(date, productId, brandId);

        // Then
        StepVerifier.create(result)
                .expectNextMatches(p -> assertEqual(PRICE_DOCUMENT_1, p))
                .expectNextMatches(p -> assertEqual(PRICE_DOCUMENT_3, p))
                .verifyComplete();
    }

    @Test
    @DisplayName("Petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)")
    void test5() {
        // Given
        var date = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        var productId = "35455";
        var brandId = "1";

        // When
        var result = priceDocumentRepository.findPricesBy(date, productId, brandId);

        // Then
        StepVerifier.create(result)
                .expectNextMatches(p -> assertEqual(PRICE_DOCUMENT_1, p))
                .expectNextMatches(p -> assertEqual(PRICE_DOCUMENT_4, p))
                .expectComplete()
                .verify();
    }

    private boolean assertEqual(PriceDocument expected, PriceDocument actual) {
        if (expected == null && actual == null) {
            return true;
        } else if (expected == null) {
            throw new AssertionError("expected: null, actual: " + actual);
        } else if (actual == null) {
            throw new AssertionError("expected: " + expected + ", actual: null");
        } else {
            Assertions.assertEquals(expected.getProductId(), actual.getProductId());
            Assertions.assertEquals(expected.getBrandId(), actual.getBrandId());
            Assertions.assertEquals(expected.getPriceList(), actual.getPriceList());
            Assertions.assertEquals(expected.getStartDate(), actual.getStartDate());
            Assertions.assertEquals(expected.getEndDate(), actual.getEndDate());
            Assertions.assertEquals(expected.getValue().doubleValue(), actual.getValue().doubleValue());
            Assertions.assertEquals(expected.getCurrency(), actual.getCurrency());
            Assertions.assertEquals(expected.getPriority(), actual.getPriority());
            return true;
        }
    }
}
