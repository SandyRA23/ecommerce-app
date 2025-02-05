package com.sra.warehouse.service.domain;

import com.sra.domain.event.publisher.DomainEventPublisher;
import com.sra.warehouse.service.domain.entity.Warehouse;
import com.sra.warehouse.service.domain.entity.WarehouseItem;
import com.sra.warehouse.service.domain.event.WarehouseCreatedEvent;
import com.sra.warehouse.service.domain.valueobject.WarehouseStatus;

public interface WarehouseDomainService {
    WarehouseCreatedEvent validateAndInitializeWarehouse(Warehouse warehouse, DomainEventPublisher<WarehouseCreatedEvent> warehouseCreatedEventDomainEventPublisher);

    void addItemToWarehouse(Warehouse warehouse, WarehouseItem item);
    void removeItemFromWarehouse(Warehouse warehouse, WarehouseItem item);
    void updateWarehouseStatus(Warehouse warehouse, WarehouseStatus newStatus);
    boolean hasSufficientCapacity(Warehouse warehouse, int quantity);
}
