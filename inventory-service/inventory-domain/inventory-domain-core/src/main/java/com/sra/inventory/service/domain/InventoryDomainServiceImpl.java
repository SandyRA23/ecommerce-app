package com.sra.inventory.service.domain;

import com.sra.domain.event.publisher.DomainEventPublisher;
import com.sra.domain.valueobject.InventoryStatus;
import com.sra.inventory.service.domain.entity.Inventory;
import com.sra.inventory.service.domain.event.InventoryCancelledEvent;
import com.sra.inventory.service.domain.event.InventoryCreatedEvent;

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
            DomainEventPublisher<InventoryCreatedEvent> inventoryCreatedEventDomainEventPublisher) {

        inventory.validateInventory();
        inventory.setStockQuantity(stockQuantity);
        inventory.setReservedQuantity(reservedQuantity);
        inventory.setInventoryStatus(InventoryStatus.CREATED);

        // log.info("Inventory with id: {} is initiated", inventory.getId().getValue());
        return new InventoryCreatedEvent(
                inventory,
                ZonedDateTime.now(ZoneId.of(UTC)),
                inventoryCreatedEventDomainEventPublisher
        );
    }

    @Override
    public InventoryCreatedEvent stockMutation(
            Inventory inventory,
            DomainEventPublisher<InventoryCreatedEvent> inventoryCreatedEventDomainEventPublisher) {

        inventory.stockMutation();
        return new InventoryCreatedEvent(
                inventory,
                ZonedDateTime.now(ZoneId.of(UTC)),
                inventoryCreatedEventDomainEventPublisher
        );
    }

    @Override
    public InventoryCancelledEvent cancelStockMutation(
            Inventory inventory,
            List<String> failureMessages,
            DomainEventPublisher<InventoryCancelledEvent> inventoryCancelledEventDomainEventPublisher) {

        inventory.initCancelStockMutation(failureMessages);
        inventory.cancelStockMutation(failureMessages);
        return new InventoryCancelledEvent(
                inventory,
                ZonedDateTime.now(ZoneId.of(UTC)),
                inventoryCancelledEventDomainEventPublisher
        );
    }
}
