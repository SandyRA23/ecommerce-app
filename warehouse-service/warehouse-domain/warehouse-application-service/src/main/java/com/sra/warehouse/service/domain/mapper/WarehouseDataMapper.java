package com.sra.warehouse.service.domain.mapper;

import com.sra.domain.valueobject.ProductId;
import com.sra.domain.valueobject.WarehouseId;
import com.sra.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.sra.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.sra.warehouse.service.domain.entity.Warehouse;
import com.sra.warehouse.service.domain.entity.WarehouseItem;
import com.sra.warehouse.service.domain.valueobject.WarehouseStatus;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class WarehouseDataMapper {
    public Warehouse createWarehouseCommandToWarehouse(CreateWarehouseCommand createWarehouseCommand) {
        WarehouseId warehouseId = new WarehouseId(UUID.randomUUID());
        return Warehouse.builder()
                .name(createWarehouseCommand.getName())
                .location(createWarehouseCommand.getLocation())
                .capacity(createWarehouseCommand.getCapacity())
                .items(warehouseItemDtoToEntity(createWarehouseCommand.getItems()))
                .build();
    }

    public CreateWarehouseResponse warehouseToCreateWarehouseResponse(Warehouse warehouse) {
        return CreateWarehouseResponse.builder()
                .warehouseId(warehouse.getId().getValue())
                .name(warehouse.getName())
                .warehouseStatus(WarehouseStatus.valueOf(warehouse.getWarehouseStatus().toString()))
                .build();
    }

    private List<WarehouseItem> warehouseItemDtoToEntity(@NotNull List<com.sra.warehouse.service.domain.dto.create.WarehouseItem> warehouseItemDto) {
        return warehouseItemDto.stream()
                .map(warehouseItem ->
                        WarehouseItem.builder()
                                .productId(new ProductId(warehouseItem.getProductId().getValue()))
                                .quantity(warehouseItem.getQuantity())
                                .build()).collect(Collectors.toList());
    }

}
