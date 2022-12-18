package me.dserrano.inditex.prices.infrastructure.outbound.mongo.mapper;

import me.dserrano.inditex.prices.domain.model.Price;
import me.dserrano.inditex.prices.infrastructure.outbound.mongo.model.PriceDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceDocumentMapper {

    Price toPrice(PriceDocument priceDocument);

}
