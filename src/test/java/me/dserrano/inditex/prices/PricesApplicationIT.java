package me.dserrano.inditex.prices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
class PricesApplicationIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("Petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void test1() throws Exception {
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
    void test2() throws Exception {
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
    void test3() throws Exception {
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
    void test4() throws Exception {
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
    void test5() throws Exception {
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
    void testEmptyResult() throws Exception {
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
