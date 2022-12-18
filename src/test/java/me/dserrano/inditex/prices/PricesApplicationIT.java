package me.dserrano.inditex.prices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import reactor.core.publisher.Flux;

import java.util.List;

import static me.dserrano.inditex.prices.infrastructure.outbound.mongo.model.PriceDocumentMother.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWebTestClient
@Testcontainers
@DirtiesContext
class PricesApplicationIT {

    @Container
    static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:6.0.3"));

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void beforeEach() {
        Flux.fromIterable(
                List.of(PRICE_DOCUMENT_1, PRICE_DOCUMENT_2, PRICE_DOCUMENT_3, PRICE_DOCUMENT_4)
        ).subscribe(price -> mongoTemplate.save(price, "prices").block());
    }

    @Test
    @DisplayName("Petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void test1() {
        webTestClient.
                get()
                .uri(uriBuilder -> uriBuilder
                        .path("/prices")
                        .queryParam("date", "2020-06-14T10:00:00")
                        .queryParam("product-id", "35455")
                        .queryParam("brand-id", "1")
                        .build()
                )
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.getType())
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.product-id").isEqualTo("35455")
                .jsonPath("$.brand-id").isEqualTo("1")
                .jsonPath("$.price-list").isEqualTo("1")
                .jsonPath("$.start-date").isEqualTo("2020-06-14T00:00:00")
                .jsonPath("$.end-date").isEqualTo("2020-12-31T23:59:59")
                .jsonPath("$.price").isEqualTo(35.5)
                .jsonPath("$.currency").isEqualTo("EUR");
    }

    @Test
    @DisplayName("Petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void test2() {
        webTestClient.
                get()
                .uri(uriBuilder -> uriBuilder
                        .path("/prices")
                        .queryParam("date", "2020-06-14T16:00:00")
                        .queryParam("product-id", "35455")
                        .queryParam("brand-id", "1")
                        .build()
                )
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.getType())
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.product-id").isEqualTo("35455")
                .jsonPath("$.brand-id").isEqualTo("1")
                .jsonPath("$.price-list").isEqualTo("2")
                .jsonPath("$.start-date").isEqualTo("2020-06-14T15:00:00")
                .jsonPath("$.end-date").isEqualTo("2020-06-14T18:30:00")
                .jsonPath("$.price").isEqualTo(25.45)
                .jsonPath("$.currency").isEqualTo("EUR");
    }

    @Test
    @DisplayName("Petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void test3() {
        webTestClient.
                get()
                .uri(uriBuilder -> uriBuilder
                        .path("/prices")
                        .queryParam("date", "2020-06-14T21:00:00")
                        .queryParam("product-id", "35455")
                        .queryParam("brand-id", "1")
                        .build()
                )
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.getType())
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.product-id").isEqualTo("35455")
                .jsonPath("$.brand-id").isEqualTo("1")
                .jsonPath("$.price-list").isEqualTo("1")
                .jsonPath("$.start-date").isEqualTo("2020-06-14T00:00:00")
                .jsonPath("$.end-date").isEqualTo("2020-12-31T23:59:59")
                .jsonPath("$.price").isEqualTo(35.5)
                .jsonPath("$.currency").isEqualTo("EUR");
    }

    @Test
    @DisplayName("Petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)")
    void test4() {
        webTestClient.
                get()
                .uri(uriBuilder -> uriBuilder
                        .path("/prices")
                        .queryParam("date", "2020-06-15T10:00:00")
                        .queryParam("product-id", "35455")
                        .queryParam("brand-id", "1")
                        .build()
                )
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.getType())
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.product-id").isEqualTo("35455")
                .jsonPath("$.brand-id").isEqualTo("1")
                .jsonPath("$.price-list").isEqualTo("3")
                .jsonPath("$.start-date").isEqualTo("2020-06-15T00:00:00")
                .jsonPath("$.end-date").isEqualTo("2020-06-15T11:00:00")
                .jsonPath("$.price").isEqualTo(35.5)
                .jsonPath("$.currency").isEqualTo("EUR");
    }

    @Test
    @DisplayName("Petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)")
    void test5() {
        webTestClient.
                get()
                .uri(uriBuilder -> uriBuilder
                        .path("/prices")
                        .queryParam("date", "2020-06-16T21:00:00")
                        .queryParam("product-id", "35455")
                        .queryParam("brand-id", "1")
                        .build()
                )
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.getType())
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.product-id").isEqualTo("35455")
                .jsonPath("$.brand-id").isEqualTo("1")
                .jsonPath("$.price-list").isEqualTo("4")
                .jsonPath("$.start-date").isEqualTo("2020-06-15T16:00:00")
                .jsonPath("$.end-date").isEqualTo("2020-12-31T23:59:59")
                .jsonPath("$.price").isEqualTo(38.95)
                .jsonPath("$.currency").isEqualTo("EUR");
    }

    @Test
    @DisplayName("Petición a las 21:00 del día 16/06/2021 del producto 35455 para la brand 1 (ZARA)")
    void testEmptyResult() {
        webTestClient.
                get()
                .uri(uriBuilder -> uriBuilder
                        .path("/prices")
                        .queryParam("date", "2021-06-16T21:00:00")
                        .queryParam("product-id", "35455")
                        .queryParam("brand-id", "1")
                        .build()
                )
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.getType())
                .exchange()
                .expectStatus().isNoContent();
    }
}
