package me.dserrano.inditex.prices.infrastructure.inbound.rest;

import me.dserrano.inditex.prices.domain.model.PriceMother;
import me.dserrano.inditex.prices.domain.ports.primary.PricesService;
import me.dserrano.inditex.prices.infrastructure.inbound.rest.mapper.PriceMapper;
import me.dserrano.inditex.prices.infrastructure.inbound.rest.model.PricesResponseMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;

@WebFluxTest(PricesController.class)
@ExtendWith(MockitoExtension.class)
public class PricesControllerIT {

    @MockBean
    private PricesService pricesService;

    @MockBean
    private PriceMapper priceMapper;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("Given a valid date, product-id and brand-id that results in a price, then a 200 response is received with the price in the body")
    public void validRequestReturnOkWithPrice() throws Exception {

        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        String productId = "35455";
        String brandId = "1";

        when(pricesService.getPricesBy(date, productId, brandId)).thenReturn(Optional.of(PriceMother.PRICE_1));
        when(priceMapper.toPricesResponse(PriceMother.PRICE_1)).thenReturn(PricesResponseMother.PRICES_RESPONSE_1);

        webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/prices")
                        .queryParam("date", date.toString())
                        .queryParam("product-id", productId)
                        .queryParam("brand-id", brandId)
                        .build()
                )
                .exchange()
                .expectStatus().isOk()
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
    @DisplayName("Given a valid date, product-id and brand-id that don't find any price, then a 204 response is received")
    public void validRequestReturnNoContent() throws Exception {

        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        String productId = "35455";
        String brandId = "1";

        webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/prices")
                        .queryParam("date", date.toString())
                        .queryParam("product-id", productId)
                        .queryParam("brand-id", brandId)
                        .build()
                )
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    @DisplayName("Given something that is not a date, then a 400 response is received")
    public void somethingThatIsNotADateReturnsKo() throws Exception {
        webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/prices")
                        .queryParam("date", "An invalid date")
                        .queryParam("product-id", "35455")
                        .queryParam("brand-id", "1")
                        .build()
                )
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("Given a date with an invalid format, then a 400 response is received")
    public void invalidDateReturnsKo() throws Exception {
        webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/prices")
                        .queryParam("date", "2020/06/14T10:00:00")
                        .queryParam("product-id", "35455")
                        .queryParam("brand-id", "1")
                        .build()
                )
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("Given a request without date, then a 400 response is received")
    public void withoutDateReturnsKo() throws Exception {
        webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/prices")
                        .queryParam("product-id", "35455")
                        .queryParam("brand-id", "1")
                        .build()
                )
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("Given a request without product-id, then a 400 response is received")
    public void withoutPriceIdReturnsKo() throws Exception {
        webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/prices")
                        .queryParam("date", "2020-06-14T10:00:00")
                        .queryParam("brand-id", "1")
                        .build()
                )
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("Given a request without brand-id, then a 400 response is received")
    public void withoutBrandIdReturnsKo() throws Exception {
        webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/prices")
                        .queryParam("date", "2020-06-14T10:00:00")
                        .queryParam("product-id", "35455")
                        .build()
                )
                .exchange()
                .expectStatus().isBadRequest();
    }
}
