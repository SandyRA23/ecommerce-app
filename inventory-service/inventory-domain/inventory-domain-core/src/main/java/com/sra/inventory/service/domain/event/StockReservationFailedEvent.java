package com.sra.inventory.service.domain.event;

import com.sra.domain.event.publisher.DomainEventPublisher;
import com.sra.inventory.service.domain.entity.Inventory;

import java.time.ZonedDateTime;

public class StockReservationFailedEvent extends InventoryEvent {
    private final DomainEventPublisher<StockReservationFailedEvent> stockReservationFailedEventPublisher;
    private final int requestedQuantity;

    public StockReservationFailedEvent(Inventory inventory,
                              ZonedDateTime createdAt,
                              DomainEventPublisher<StockReservationFailedEvent> stockReservationFailedEventPublisher,
                              int requestedQuantity) {
        super(inventory, createdAt);
        this.stockReservationFailedEventPublisher = stockReservationFailedEventPublisher;
        this.requestedQuantity = requestedQuantity;
    }

    @Override
    public void fire() {
        stockReservationFailedEventPublisher.publish(this);
    }
}