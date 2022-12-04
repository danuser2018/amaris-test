package me.dserrano.inditex.prices.infrastructure.outbound.h2.mapper;

import me.dserrano.inditex.prices.domain.model.Price;
import me.dserrano.inditex.prices.infrastructure.outbound.h2.model.PriceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

    Price toPrice(PriceEntity priceEntity);

}
