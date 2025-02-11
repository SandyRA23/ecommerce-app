package com.sra.inventory.service.domain;

import com.sra.domain.event.publisher.DomainEventPublisher;
import com.sra.domain.valueobject.WarehouseId;
import com.sra.inventory.service.domain.entity.Inventory;
import com.sra.inventory.service.domain.event.*;

import java.util.List;

public interface InventoryDomainService {
    InventoryCreatedEvent validateAndInitiateInventory(
            Inventory inventory,
            int stockQuantity,
            int reservedQuantity,
            DomainEventPublisher<InventoryCreatedEvent> inventoryCreatedEventDomainEventPublisher
    );

    InventoryUpdatedEvent updateInventory(
            Inventory inventory,
            int newStockQuantity,
            DomainEventPublisher<InventoryUpdatedEvent> inventoryUpdatedEventPublisher
    );

    StockReservedEvent reserveStock(
            Inventory inventory,
            int quantity,
            DomainEventPublisher<StockReservedEvent> stockReservedEventPublisher
    );

    StockReservationFailedEvent handleStockReservationFailure(
            Inventory inventory,
            int requestedQuantity,
            List<String> failureMessages,
            DomainEventPublisher<StockReservationFailedEvent> stockReservationFailedEventPublisher
    );

    StockReleasedEvent releaseStock(
            Inventory inventory,
            int quantity,
            DomainEventPublisher<StockReleasedEvent> stockReleasedEventPublisher
    );

    StockMutatedEvent stockMutation(
            Inventory inventory,
            WarehouseId sourceWarehouseId,
            WarehouseId destinationWarehouseId,
            int movedQuantity,
            DomainEventPublisher<StockMutatedEvent> stockMutatedEventPublisher
    );
}
