package com.sra.inventory.service.domain.event;

import com.sra.domain.event.publisher.DomainEventPublisher;
import com.sra.inventory.service.domain.entity.Inventory;

import java.time.ZonedDateTime;

public class InventoryCreatedEvent extends InventoryEvent {
    private final DomainEventPublisher<InventoryCreatedEvent> inventoryCreatedEventPublisher;

    public InventoryCreatedEvent(Inventory inventory,
                                 ZonedDateTime createdAt,
                                 DomainEventPublisher<InventoryCreatedEvent> inventoryCreatedEventPublisher) {
        super(inventory, createdAt);
        this.inventoryCreatedEventPublisher = inventoryCreatedEventPublisher;
    }

    @Override
    public void fire() {
        inventoryCreatedEventPublisher.publish(this);
    }
}
