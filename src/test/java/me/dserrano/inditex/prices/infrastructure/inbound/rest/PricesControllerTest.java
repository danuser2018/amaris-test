package me.dserrano.inditex.prices.infrastructure.inbound.rest;

import me.dserrano.inditex.prices.domain.ports.primary.GetPricePort;
import me.dserrano.inditex.prices.infrastructure.inbound.rest.mapper.PriceMapper;
import me.dserrano.inditex.prices.infrastructure.inbound.rest.model.PricesResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static me.dserrano.inditex.prices.domain.model.PriceMother.PRICE;
import static me.dserrano.inditex.prices.infrastructure.inbound.rest.model.PricesResponseMother.PRICES_RESPONSE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PricesControllerTest {

    private PricesController classToTest;
    private GetPricePort getPricePort;

    private final LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
    private final String productId = "35455";
    private final String brandId = "1";

    @BeforeEach
    private void setupMocks(@Mock GetPricePort getPricePort, @Mock PriceMapper priceMapper) {
        this.getPricePort = getPricePort;
        classToTest = new PricesController(getPricePort, priceMapper);
        when(getPricePort.get(date, productId, brandId)).thenReturn(PRICE);
        when(priceMapper.toPricesResponse(PRICE)).thenReturn(PRICES_RESPONSE);
    }

    @Test
    @DisplayName("Given a valid request that produces a result then return an Ok response with a price")
    public void validRequestReturnsOKAndPrice() {
        // When
        ResponseEntity<PricesResponse> result = classToTest.getPrices(date, productId, brandId);

        // Then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(PRICES_RESPONSE, result.getBody());
    }

    @Test
    @DisplayName("Given a valid request then domain is invoke")
    public void validRequestInvokeDomain() {
        // When
        ResponseEntity<PricesResponse> result = classToTest.getPrices(date, productId, brandId);

        // Then
        verify(getPricePort).get(date, productId, brandId);
    }

}
