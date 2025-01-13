package com.sra.inventory.service.domain.event;

import com.sra.domain.event.publisher.DomainEventPublisher;
import com.sra.inventory.service.domain.entity.Inventory;

import java.time.ZonedDateTime;

public class StockMutationCreatedEvent extends InventoryEvent {
    private final DomainEventPublisher<StockMutationCreatedEvent> inventoryCreatedEventPublisher;

    public StockMutationCreatedEvent(Inventory inventory,
                                 ZonedDateTime createdAt,
                                 DomainEventPublisher<StockMutationCreatedEvent> inventoryCreatedEventPublisher) {
        super(inventory, createdAt);
        this.inventoryCreatedEventPublisher = inventoryCreatedEventPublisher;
    }

    @Override
    public void fire() {
        inventoryCreatedEventPublisher.publish(this);
    }
}