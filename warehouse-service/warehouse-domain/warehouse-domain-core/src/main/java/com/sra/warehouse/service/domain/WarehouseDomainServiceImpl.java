package com.sra.warehouse.service.domain;

import com.sra.domain.event.publisher.DomainEventPublisher;
import com.sra.warehouse.service.domain.entity.Warehouse;
import com.sra.warehouse.service.domain.entity.WarehouseItem;
import com.sra.warehouse.service.domain.event.WarehouseCreatedEvent;
import com.sra.warehouse.service.domain.exception.WarehouseDomainException;
import com.sra.warehouse.service.domain.valueobject.WarehouseStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.sra.domain.DomainConstants.UTC;

public class WarehouseDomainServiceImpl implements WarehouseDomainService {

    @Override
    public WarehouseCreatedEvent validateAndInitializeWarehouse(Warehouse warehouse, DomainEventPublisher<WarehouseCreatedEvent>
            warehouseCreatedEventDomainEventPublisher) {
        if (warehouse.getLocation() == null) {
            throw new WarehouseDomainException("Warehouse location is required!");
        }
        if (warehouse.getCapacity() == null || !warehouse.getCapacity().isValid()) {
            throw new WarehouseDomainException("Invalid warehouse capacity!");
        }
        warehouse.initializeWarehouse();
        return new WarehouseCreatedEvent(warehouse, ZonedDateTime.now(ZoneId.of(UTC)), warehouseCreatedEventDomainEventPublisher);
    }

    @Override
    public void addItemToWarehouse(Warehouse warehouse, WarehouseItem item) {
        if (!hasSufficientCapacity(warehouse, item.getQuantity())) {
            throw new WarehouseDomainException("Insufficient capacity to add item!");
        }
        warehouse.getItems().add(item);
    }

    @Override
    public void removeItemFromWarehouse(Warehouse warehouse, WarehouseItem item) {
        if (!warehouse.getItems().contains(item)) {
            throw new WarehouseDomainException("Item not found in warehouse!");
        }
        warehouse.getItems().remove(item);
    }

    @Override
    public void updateWarehouseStatus(Warehouse warehouse, WarehouseStatus newStatus) {
        if (warehouse.getWarehouseStatus() == WarehouseStatus.CLOSED && newStatus != WarehouseStatus.CLOSED) {
            throw new WarehouseDomainException("Cannot change status of a closed warehouse!");
        }
        warehouse.setWarehouseStatus(newStatus);
    }

    @Override
    public boolean hasSufficientCapacity(Warehouse warehouse, int quantity) {
        int currentCapacity = warehouse.getItems().stream()
                .mapToInt(WarehouseItem::getQuantity)
                .sum();
        return (currentCapacity + quantity) <= warehouse.getCapacity().getMaxCapacity();
    }
}
