package com.sra.warehouse.service.messaging.mapper;

import com.sra.warehouse.service.domain.dto.message.ProductMutationRequest;
import com.sra.warehouse.service.domain.dto.message.ProductMutationResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class WarehouseMessagingDataMapper {

    public ProductMutationRequest productMutationRequestAvroModelToProductMutationRequest(ProductMutationRequestAvroModel productMutationRequestAvroModel) {
        return ProductMutationRequest.builder()
                .id(UUID.fromString(productMutationRequestAvroModel.getId()))
                .warehouseId(UUID.fromString(productMutationRequestAvroModel.getWarehouseId()))
                .productId(UUID.fromString(productMutationRequestAvroModel.getProductId()))
                .mutationType(productMutationRequestAvroModel.getMutationType())
                .quantity(productMutationRequestAvroModel.getQuantity())
                .createdAt(productMutationRequestAvroModel.getCreatedAt().toInstant())
                .build();
    }

    public ProductMutationResponse productMutationResponseAvroModelToProductMutationResponse(ProductMutationResponseAvroModel productMutationResponseAvroModel) {
        return ProductMutationResponse.builder()
                .id(UUID.fromString(productMutationResponseAvroModel.getId()))
                .warehouseId(UUID.fromString(productMutationResponseAvroModel.getWarehouseId()))
                .productId(UUID.fromString(productMutationResponseAvroModel.getProductId()))
                .mutationStatus(productMutationResponseAvroModel.getMutationStatus())
                .mutationType(productMutationResponseAvroModel.getMutationType())
                .quantity(productMutationResponseAvroModel.getQuantity())
                .timestamp(productMutationResponseAvroModel.getTimestamp())
                .build();
    }

    public ProductMutationRequestAvroModel productMutationRequestToProductMutationRequestAvroModel(ProductMutationRequest productMutationRequest) {
        return ProductMutationRequestAvroModel.newBuilder()
                .setId(productMutationRequest.getId().toString())
                .setWarehouseId(productMutationRequest.getWarehouseId().toString())
                .setProductId(productMutationRequest.getProductId().toString())
                .setMutationType(productMutationRequest.getMutationType())
                .setQuantity(productMutationRequest.getQuantity())
                .setCreatedAt(productMutationRequest.getCreatedAt())
                .build();
    }

    public ProductMutationResponseAvroModel productMutationResponseToProductMutationResponseAvroModel(ProductMutationResponse productMutationResponse) {
        return ProductMutationResponseAvroModel.newBuilder()
                .setId(productMutationResponse.getId().toString())
                .setWarehouseId(productMutationResponse.getWarehouseId().toString())
                .setProductId(productMutationResponse.getProductId().toString())
                .setMutationStatus(productMutationResponse.getMutationStatus())
                .setMutationType(productMutationResponse.getMutationType())
                .setQuantity(productMutationResponse.getQuantity())
                .setTimestamp(productMutationResponse.getTimestamp())
                .build();
    }
}
