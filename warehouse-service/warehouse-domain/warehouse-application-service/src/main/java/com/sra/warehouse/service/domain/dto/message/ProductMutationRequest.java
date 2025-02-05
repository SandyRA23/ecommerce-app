package com.sra.warehouse.service.domain.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
public class ProductMutationRequest {

    private String id;
    private String warehouseId;
    private String productId;
    private String mutationType;
    private int quantity;
    private Instant createdAt;

}
