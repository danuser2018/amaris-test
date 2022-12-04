package me.dserrano.inditex.prices.infrastructure.inbound.rest.mapper;

import me.dserrano.inditex.prices.domain.model.Price;
import me.dserrano.inditex.prices.infrastructure.inbound.rest.model.PricesResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    PricesResponse toPricesResponse(Price price);

}
