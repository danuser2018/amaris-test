package me.dserrano.inditex.prices.infrastructure.outbound.mongo.repository;

import me.dserrano.inditex.prices.infrastructure.outbound.mongo.model.PriceDocument;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
public interface PriceDocumentRepository extends ReactiveMongoRepository<PriceDocument, String> {

    @Query("{startDate: {$lt: ?0}, endDate: {$gt: ?0}, productId: ?1, brandId: ?2}")
    Flux<PriceDocument> findPricesBy(LocalDateTime date, String productId, String brandId);
}
