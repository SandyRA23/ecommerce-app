package com.sra.inventory.service.domain;

import com.sra.domain.event.publisher.DomainEventPublisher;
import com.sra.domain.valueobject.InventoryStatus;
import com.sra.domain.valueobject.WarehouseId;
import com.sra.inventory.service.domain.entity.Inventory;
import com.sra.inventory.service.domain.event.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class InventoryDomainServiceImpl implements InventoryDomainService {

    private static final String UTC = "UTC";

    @Override
    public InventoryCreatedEvent validateAndInitiateInventory(
            Inventory inventory,
            int stockQuantity,
            int reservedQuantity,
            DomainEventPublisher<InventoryCreatedEvent> inventoryCreatedEventPublisher) {

        inventory.validateInventory();
        inventory.setStockQuantity(stockQuantity);
        inventory.setReservedQuantity(reservedQuantity);
        inventory.setInventoryStatus(InventoryStatus.CREATED);

        return new InventoryCreatedEvent(
                inventory,
                ZonedDateTime.now(ZoneId.of(UTC)),
                inventoryCreatedEventPublisher
        );
    }

    @Override
    public InventoryUpdatedEvent updateInventory(
            Inventory inventory,
            int newStockQuantity,
            DomainEventPublisher<InventoryUpdatedEvent> inventoryUpdatedEventPublisher) {

        inventory.setStockQuantity(newStockQuantity);

        return new InventoryUpdatedEvent(
                inventory,
                ZonedDateTime.now(ZoneId.of(UTC)),
                inventoryUpdatedEventPublisher
        );
    }

    @Override
    public StockReservedEvent reserveStock(
            Inventory inventory,
            int quantity,
            DomainEventPublisher<StockReservedEvent> stockReservedEventPublisher) {

        inventory.reserveStock(quantity);

        return new StockReservedEvent(
                inventory,
                ZonedDateTime.now(ZoneId.of(UTC)),
                stockReservedEventPublisher,
                quantity
        );
    }

    @Override
    public StockReservationFailedEvent handleStockReservationFailure(
            Inventory inventory,
            int requestedQuantity,
            List<String> failureMessages,
            DomainEventPublisher<StockReservationFailedEvent> stockReservationFailedEventPublisher) {

        inventory.failStockReservation(failureMessages);

        return new StockReservationFailedEvent(
                inventory,
                ZonedDateTime.now(ZoneId.of(UTC)),
                stockReservationFailedEventPublisher,
                requestedQuantity
        );
    }

    @Override
    public StockReleasedEvent releaseStock(
            Inventory inventory,
            int quantity,
            DomainEventPublisher<StockReleasedEvent> stockReleasedEventPublisher) {

        inventory.releaseStock(quantity);

        return new StockReleasedEvent(
                inventory,
                ZonedDateTime.now(ZoneId.of(UTC)),
                stockReleasedEventPublisher,
                quantity
        );
    }

    @Override
    public StockMutatedEvent stockMutation(
            Inventory inventory,
            WarehouseId sourceWarehouseId,
            WarehouseId destinationWarehouseId,
            int quantity,
            DomainEventPublisher<StockMutatedEvent> stockMutatedEventPublisher) {

        inventory.mutateStock(sourceWarehouseId, destinationWarehouseId, quantity);

        return new StockMutatedEvent(
                inventory,
                ZonedDateTime.now(ZoneId.of(UTC)),
                sourceWarehouseId,
                destinationWarehouseId,
                stockMutatedEventPublisher,
                quantity
        );
    }
}
