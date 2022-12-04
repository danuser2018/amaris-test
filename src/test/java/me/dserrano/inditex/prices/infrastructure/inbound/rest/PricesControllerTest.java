package me.dserrano.inditex.prices.infrastructure.inbound.rest;

import me.dserrano.inditex.prices.domain.ports.primary.PricesService;
import me.dserrano.inditex.prices.infrastructure.inbound.rest.mapper.PriceMapper;
import me.dserrano.inditex.prices.infrastructure.inbound.rest.model.PricesResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static me.dserrano.inditex.prices.domain.model.PriceMother.PRICE_1;
import static me.dserrano.inditex.prices.infrastructure.inbound.rest.model.PricesResponseMother.PRICES_RESPONSE_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
public class PricesControllerTest {
    @Mock
    private PricesService pricesService;

    @Mock
    private PriceMapper priceMapper;

    @InjectMocks
    private PricesController classToTest;

    private final LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
    private final String productId = "35455";
    private final String brandId = "1";

    @Test
    @DisplayName("Given a valid request that produces a result then return an Ok response with a price")
    public void validRequestReturnsOKAndPrice() {
        // Given
        when(pricesService.getPricesBy(date, productId, brandId)).thenReturn(Mono.just(PRICE_1));

        // When
        Mono<ResponseEntity<PricesResponse>> result = classToTest.getPrices(date, productId, brandId);

        // Then
        StepVerifier.create(result)
                .assertNext(response -> {
                    assertEquals(OK, response.getStatusCode());
                    assertEquals(PRICES_RESPONSE_1, response.getBody());
                })
                .expectComplete();
    }

    @Test
    @DisplayName("Given a valid request then domain is invoke")
    public void validRequestInvokeDomain() {
        // Given
        when(pricesService.getPricesBy(date, productId, brandId)).thenReturn(Mono.just(PRICE_1));

        // When
        classToTest.getPrices(date, productId, brandId);

        // Then
        verify(pricesService).getPricesBy(date, productId, brandId);
    }

    @Test
    @DisplayName("Given a valid request that produces no price then return an empty Ok response")
    public void validRequestReturnsNoPrice() {
        // Given
        when(pricesService.getPricesBy(date, productId, brandId)).thenReturn(Mono.empty());

        // When
        Mono<ResponseEntity<PricesResponse>> result = classToTest.getPrices(date, productId, brandId);

        // Then
        StepVerifier.create(result)
                .assertNext(response -> {
                    assertEquals(NO_CONTENT, response.getStatusCode());
                    assertNull(response.getBody());
                });
    }
}
