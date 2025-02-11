package com.sra.inventory.service.domain.event;

import com.sra.domain.event.publisher.DomainEventPublisher;
import com.sra.inventory.service.domain.entity.Inventory;

import java.time.ZonedDateTime;

public class InventoryUpdatedEvent extends InventoryEvent {
    private final DomainEventPublisher<InventoryUpdatedEvent> inventoryUpdatedEventPublisher;

    public InventoryUpdatedEvent(Inventory inventory,
                                 ZonedDateTime updatedAt,
                                 DomainEventPublisher<InventoryUpdatedEvent> inventoryUpdatedEventPublisher) {
        super(inventory, updatedAt);
        this.inventoryUpdatedEventPublisher = inventoryUpdatedEventPublisher;
    }

    @Override
    public void fire() {
        inventoryUpdatedEventPublisher.publish(this);
    }
}