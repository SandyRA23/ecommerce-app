package com.sra.warehouse.service.dataaccess.warehouse.mapper;

import com.sra.domain.valueobject.ProductId;
import com.sra.domain.valueobject.WarehouseId;
import com.sra.warehouse.service.dataaccess.warehouse.entity.WarehouseCapacityEntity;
import com.sra.warehouse.service.dataaccess.warehouse.entity.WarehouseEntity;
import com.sra.warehouse.service.dataaccess.warehouse.entity.WarehouseItemEntity;
import com.sra.warehouse.service.dataaccess.warehouse.entity.WarehouseLocationEntity;
import com.sra.warehouse.service.domain.entity.Product;
import com.sra.warehouse.service.domain.entity.Warehouse;
import com.sra.warehouse.service.domain.entity.WarehouseItem;
import com.sra.warehouse.service.domain.valueobject.Capacity;
import com.sra.warehouse.service.domain.valueobject.Location;
import com.sra.warehouse.service.domain.valueobject.WarehouseItemId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WarehouseDataAccessMapper {

    public WarehouseEntity warehouseToWarehouseEntity(Warehouse warehouse) {
        WarehouseEntity warehouseEntity = WarehouseEntity.builder()
                .id(warehouse.getId().getValue())
                .name(warehouse.getName())
                .location(locationToLocationEntity(warehouse.getLocation()))
                .capacity(capacityToCapacityEntity(warehouse.getCapacity()))
                .items(warehouseItemsToWarehouseItemEntities(warehouse.getItems()))
                .warehouseStatus(warehouse.getWarehouseStatus())
                .build();
        warehouseEntity.getLocation().setWarehouse(warehouseEntity);
        warehouseEntity.getCapacity().setWarehouse(warehouseEntity);
        warehouseEntity.getItems().forEach(warehouseItemEntity -> warehouseItemEntity.setWarehouse(warehouseEntity));

        return warehouseEntity;
    }

    public Warehouse warehouseEntityToWarehouse(WarehouseEntity warehouseEntity) {
        return Warehouse.builder()
                .warehouseId(new WarehouseId(warehouseEntity.getId()))
                .name(warehouseEntity.getName())
                .location(locationEntityToLocation(warehouseEntity.getLocation()))
                .capacity(capacityEntityToCapacity(warehouseEntity.getCapacity()))
                .items(warehouseItemEntitiesToWarehouseItems(warehouseEntity.getItems()))
                .warehouseStatus(warehouseEntity.getWarehouseStatus())
                .build();
    }

    private List<WarehouseItem> warehouseItemEntitiesToWarehouseItems(List<WarehouseItemEntity> items) {
        return items.stream()
                .map(warehouseItemEntity -> WarehouseItem.builder()
                        .warehouseItemId(new WarehouseItemId(warehouseItemEntity.getId()))
                        .warehouseId(new WarehouseId(warehouseItemEntity.getWarehouseId()))
                        .productId(new ProductId(warehouseItemEntity.getProductId()))
                        .quantity(warehouseItemEntity.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }

    private List<WarehouseItemEntity> warehouseItemsToWarehouseItemEntities(List<WarehouseItem> items) {
        return items.stream()
                .map(warehouseItem -> WarehouseItemEntity.builder()
                        .id(warehouseItem.getId().getValue())
                        .warehouseId(warehouseItem.getWarehouseId().getValue())
                        .productId(warehouseItem.getProductId().getValue())
                        .quantity(warehouseItem.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }

    private Location locationEntityToLocation(WarehouseLocationEntity location) {
        return new Location(
                location.getAddress(),
                location.getCity(),
                location.getCountry());
    }

    private WarehouseLocationEntity locationToLocationEntity(Location location) {
        return WarehouseLocationEntity.builder()
                .address(location.getAddress())
                .city(location.getCity())
                .country(location.getCountry())
                .build();
    }

    private Capacity capacityEntityToCapacity(WarehouseCapacityEntity capacity) {
        return new Capacity(
                capacity.getMaxCapacity(),
                capacity.getCurrentCapacity());

    }

    private WarehouseCapacityEntity capacityToCapacityEntity(Capacity capacity) {
        return WarehouseCapacityEntity.builder()
                .maxCapacity(capacity.getMaxCapacity())
                .currentCapacity(capacity.getCurrentCapacity())
                .build();
    }
}