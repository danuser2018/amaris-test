package me.dserrano.inditex.prices.infrastructure.inbound.rest;

import me.dserrano.inditex.prices.domain.model.Price;
import me.dserrano.inditex.prices.domain.ports.primary.PricesService;
import me.dserrano.inditex.prices.infrastructure.inbound.rest.mapper.PriceMapper;
import me.dserrano.inditex.prices.infrastructure.inbound.rest.model.PricesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;


@RestController
public class PricesController implements PriceApi {

    private final PricesService pricesService;
    private final PriceMapper priceMapper;

    @Autowired
    public PricesController(PricesService pricesService, PriceMapper priceMapper) {
        this.pricesService = pricesService;
        this.priceMapper = priceMapper;
    }


    @GetMapping(path = "prices", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PricesResponse> getPrices(
            @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam("product-id") String productId,
            @RequestParam("brand-id") String brandId
    ) {
        Optional<Price> price = pricesService.getPricesBy(date, productId, brandId);
        return price
                .map(p -> ResponseEntity.ok(priceMapper.toPricesResponse(p)))
                .orElse(ResponseEntity.noContent().build());
    }
}
