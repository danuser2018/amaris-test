package me.dserrano.inditex.prices.domain;

import me.dserrano.inditex.prices.domain.model.Price;
import me.dserrano.inditex.prices.domain.ports.secondary.PricesDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.List;

import static me.dserrano.inditex.prices.domain.model.PriceMother.PRICE_1;
import static me.dserrano.inditex.prices.domain.model.PriceMother.PRICE_2;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PricesServiceAdapterTest {
    @Mock
    private PricesDao pricesDao;

    @InjectMocks
    private PricesServiceAdapter classToTest;

    private final LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
    private final String productId = "35455";
    private final String brandId = "1";

    @Test
    @DisplayName("Given a date, productId and brandId that produces a result then a Price is returned")
    public void validParametersReturnsAPrice() {
        // Given
        when(pricesDao.getPricesBy(date, productId, brandId)).thenReturn(List.of(PRICE_1));

        // When
        Mono<Price> result = classToTest.getPricesBy(date, productId, brandId);

        // Then
        StepVerifier.create(result)
                .expectNext(PRICE_1)
                .expectComplete();
    }

    @Test
    @DisplayName("Given a date, productId and brandId then secondary port is invoked")
    public void validRequestInvokeSecondaryPort() {
        // Given
        when(pricesDao.getPricesBy(date, productId, brandId)).thenReturn(List.of(PRICE_1));

        // When
        classToTest.getPricesBy(date, productId, brandId);

        // Then
        verify(pricesDao).getPricesBy(date, productId, brandId);
    }

    @Test
    @DisplayName("Given a date, productId and brandId that results in more than one price, then the one with most priority is returned")
    public void validRequestForTwoPricesReturnsTheOneWithMostPriority() {
        // Given
        when(pricesDao.getPricesBy(date, productId, brandId)).thenReturn(List.of(PRICE_1, PRICE_2));

        // When
        Mono<Price> result = classToTest.getPricesBy(date, productId, brandId);

        // Then
        StepVerifier.create(result)
                .expectNext(PRICE_2)
                .expectComplete();
    }

    @Test
    @DisplayName("Given a date, productId and brandId that results in no price, then an empty Mono is returned")
    public void validRequestReturnsNoPrice() {
        // Given
        when(pricesDao.getPricesBy(date, productId, brandId)).thenReturn(List.of());

        // When
        Mono<Price> result = classToTest.getPricesBy(date, productId, brandId);

        // Then
        StepVerifier.create(result)
                .expectComplete();
    }
}
