package me.dserrano.inditex.prices.infrastructure.outbound.mongo;

import me.dserrano.inditex.prices.domain.model.Price;
import me.dserrano.inditex.prices.infrastructure.outbound.mongo.mapper.PriceDocumentMapper;
import me.dserrano.inditex.prices.infrastructure.outbound.mongo.repository.PriceDocumentRepository;
import org.junit.jupiter.api.BeforeEach;
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
import static me.dserrano.inditex.prices.infrastructure.outbound.mongo.model.PriceDocumentMother.PRICE_DOCUMENT_1;
import static me.dserrano.inditex.prices.infrastructure.outbound.mongo.model.PriceDocumentMother.PRICE_DOCUMENT_2;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MongoPricesDaoAdapterTest {
    @Mock
    private PriceDocumentRepository priceDocumentRepository;

    @Mock
    private PriceDocumentMapper priceDocumentMapper;

    @InjectMocks
    private MongoPricesDaoAdapter pricesDaoAdapter;

    private final LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
    private final String productId = "35455";
    private final String brandId = "1";

    @Test
    @DisplayName("Given a date, productId and brandId that produces a result then a Price is returned")
    public void validParametersReturnsAPrice() {
        // Given
        when(priceDocumentRepository.findPricesBy(date, productId, brandId)).thenReturn(Flux.just(PRICE_DOCUMENT_1));
        when(priceDocumentMapper.toPrice(PRICE_DOCUMENT_1)).thenReturn(PRICE_1);

        // When
        Flux<Price> result = pricesDaoAdapter.getPricesBy(date, productId, brandId);

        // Then
        StepVerifier.create(result)
                .expectNext(PRICE_1)
                .verifyComplete();
    }

    @Test
    @DisplayName("Given a date, productId and brandId then repository is invoked")
    public void validParametersInvokeRepository() {
        // Given
        when(priceDocumentRepository.findPricesBy(date, productId, brandId)).thenReturn(Flux.just(PRICE_DOCUMENT_1));

        // When
        pricesDaoAdapter.getPricesBy(date, productId, brandId);

        // Then
        verify(priceDocumentRepository).findPricesBy(date, productId, brandId);
    }

    @Test
    @DisplayName("Given a date, productId and brandId that produces a two results then a two prices are returned")
    public void validParametersReturnsTwoPrices() {
        // Given
        when(priceDocumentRepository.findPricesBy(date, productId, brandId)).thenReturn(
                Flux.fromIterable(List.of(PRICE_DOCUMENT_1, PRICE_DOCUMENT_2)));
        when(priceDocumentMapper.toPrice(PRICE_DOCUMENT_1)).thenReturn(PRICE_1);
        when(priceDocumentMapper.toPrice(PRICE_DOCUMENT_2)).thenReturn(PRICE_2);

        // When
        Flux<Price> result = pricesDaoAdapter.getPricesBy(date, productId, brandId);

        // Then
        StepVerifier.create(result)
                .expectNext(PRICE_1)
                .expectNext(PRICE_2)
                .verifyComplete();
    }

    @Test
    @DisplayName("Given a date, productId and brandId that not produces a result then no price is returned")
    public void validParametersReturnsNoPrice() {
        // Given
        when(priceDocumentRepository.findPricesBy(date, productId, brandId)).thenReturn(Flux.empty());

        // When
        Flux<Price> result = pricesDaoAdapter.getPricesBy(date, productId, brandId);

        // Then
        StepVerifier.create(result).verifyComplete();
    }
}