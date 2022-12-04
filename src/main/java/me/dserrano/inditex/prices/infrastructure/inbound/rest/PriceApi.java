package me.dserrano.inditex.prices.infrastructure.inbound.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import me.dserrano.inditex.prices.infrastructure.inbound.rest.model.PricesResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface PriceApi {
    @Operation(summary = "Find a price by date, product-id and brand-id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Price found",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = PricesResponse.class)
                            )
                    }),
            @ApiResponse(
                    responseCode = "204",
                    description = "No price found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request",
                    content = @Content
            )
    })
    Mono<ResponseEntity<PricesResponse>> getPrices(
            @Parameter(
                    description = "Price's application date in ISO 8601 format",
                    required = true,
                    example = "2020-06-16T21:00:00"
            ) LocalDateTime date,
            @Parameter(
                    description = "Price's product id",
                    required = true,
                    example = "35455"
            ) String productId,
            @Parameter(
                    description = "Price's brand id",
                    required = true,
                    example = "1"
            )
            String brandId
    );
}
