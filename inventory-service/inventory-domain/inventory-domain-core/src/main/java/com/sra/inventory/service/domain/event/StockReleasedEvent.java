package com.sra.inventory.service.domain.event;

import com.sra.domain.event.publisher.DomainEventPublisher;
import com.sra.inventory.service.domain.entity.Inventory;

import java.time.ZonedDateTime;

public class StockReleasedEvent extends InventoryEvent {
    private final DomainEventPublisher<StockReleasedEvent> stockReleasedEventPublisher;
    private final int releasedQuantity;

    public StockReleasedEvent(Inventory inventory,
                                       ZonedDateTime createdAt,
                                       DomainEventPublisher<StockReleasedEvent> stockReleasedEventPublisher,
                                       int releasedQuantity) {
        super(inventory, createdAt);
        this.stockReleasedEventPublisher = stockReleasedEventPublisher;
        this.releasedQuantity = releasedQuantity;
    }

    @Override
    public void fire() {
        stockReleasedEventPublisher.publish(this);
    }
}
