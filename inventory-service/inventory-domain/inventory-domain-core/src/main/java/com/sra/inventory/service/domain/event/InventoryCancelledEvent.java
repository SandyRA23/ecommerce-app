package com.sra.inventory.service.domain.event;

import com.sra.domain.event.publisher.DomainEventPublisher;
import com.sra.inventory.service.domain.entity.Inventory;

import java.time.ZonedDateTime;

public class InventoryCancelledEvent extends InventoryEvent {
    private final DomainEventPublisher<InventoryCancelledEvent> inventoryCancelledEventPublisher;

    public InventoryCancelledEvent(Inventory inventory,
                                 ZonedDateTime createdAt,
                                 DomainEventPublisher<InventoryCancelledEvent> inventoryCancelledEventPublisher) {
        super(inventory, createdAt);
        this.inventoryCancelledEventPublisher = inventoryCancelledEventPublisher;
    }

    @Override
    public void fire() {
        inventoryCancelledEventPublisher.publish(this);
    }
}
