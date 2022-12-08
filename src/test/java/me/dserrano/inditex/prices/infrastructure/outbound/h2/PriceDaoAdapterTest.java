package me.dserrano.inditex.prices.infrastructure.outbound.h2;

import me.dserrano.inditex.prices.domain.model.Price;
import me.dserrano.inditex.prices.infrastructure.outbound.h2.mapper.PriceEntityMapper;
import me.dserrano.inditex.prices.infrastructure.outbound.h2.repository.PriceEntityRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.List;

import static me.dserrano.inditex.prices.domain.model.PriceMother.PRICE_1;
import static me.dserrano.inditex.prices.domain.model.PriceMother.PRICE_2;
import static me.dserrano.inditex.prices.infrastructure.outbound.h2.model.PriceEntityMother.PRICE_ENTITY_1;
import static me.dserrano.inditex.prices.infrastructure.outbound.h2.model.PriceEntityMother.PRICE_ENTITY_2;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceDaoAdapterTest {

    @Mock
    private PriceEntityRepository priceEntityRepository;

    @Mock
    private PriceEntityMapper priceEntityMapper;

    @InjectMocks
    private PricesDaoAdapter classToTest;

    private final LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
    private final String productId = "35455";
    private final String brandId = "1";

    @Test
    @DisplayName("Given a date, productId and brandId that produces a result then a Price is returned")
    public void validParametersReturnsAPrice() {
        // Given
        when(priceEntityRepository.getPricesBy(date, productId, brandId)).thenReturn(Flux.just(PRICE_ENTITY_1));
        when(priceEntityMapper.toPrice(PRICE_ENTITY_1)).thenReturn(PRICE_1);

        // When
        Flux<Price> result = classToTest.getPricesBy(date, productId, brandId);

        // Then
        StepVerifier.create(result)
                .expectNext(PRICE_1)
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Given a date, productId and brandId then repository is invoked")
    public void validParametersInvokeRepository() {
        // Given
        when(priceEntityRepository.getPricesBy(date, productId, brandId)).thenReturn(Flux.just(PRICE_ENTITY_1));
        when(priceEntityMapper.toPrice(PRICE_ENTITY_1)).thenReturn(PRICE_1);

        // When
        Flux<Price> result = classToTest.getPricesBy(date, productId, brandId);

        // Then
        StepVerifier.create(result)
                .expectNext(PRICE_1)
                .expectComplete()
                .verify();

        // Then
        verify(priceEntityRepository).getPricesBy(date, productId, brandId);
    }

    @Test
    @DisplayName("Given a date, productId and brandId that produces a two results then a two prices are returned")
    public void validParametersReturnsTwoPrices() {
        // Given
        when(priceEntityRepository.getPricesBy(date, productId, brandId))
                .thenReturn(Flux.fromIterable(List.of(PRICE_ENTITY_1, PRICE_ENTITY_2)));
        when(priceEntityMapper.toPrice(PRICE_ENTITY_1)).thenReturn(PRICE_1);
        when(priceEntityMapper.toPrice(PRICE_ENTITY_2)).thenReturn(PRICE_2);

        // When
        Flux<Price> result = classToTest.getPricesBy(date, productId, brandId);

        // Then
        StepVerifier.create(result)
                .expectNext(PRICE_1)
                .expectNext(PRICE_2)
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Given a date, productId and brandId that not produces a result then no price is returned")
    public void validParametersReturnsNoPrice() {
        // Given
        when(priceEntityRepository.getPricesBy(date, productId, brandId)).thenReturn(Flux.empty());

        // When
        Flux<Price> result = classToTest.getPricesBy(date, productId, brandId);

        // Then
        StepVerifier.create(result)
                .expectComplete()
                .verify();
    }
}
