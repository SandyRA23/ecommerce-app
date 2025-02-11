package com.sra.inventory.service.domain.event;

import com.sra.domain.event.publisher.DomainEventPublisher;
import com.sra.inventory.service.domain.entity.Inventory;

import java.time.ZonedDateTime;

public class StockReservedEvent extends InventoryEvent {
    private final DomainEventPublisher<StockReservedEvent> stockReservedEventPublisher;
    private final int reservedQuantity;

    public StockReservedEvent(Inventory inventory,
                                 ZonedDateTime createdAt,
                                 DomainEventPublisher<StockReservedEvent> stockReservedEventPublisher,
                              int reservedQuantity) {
        super(inventory, createdAt);
        this.stockReservedEventPublisher = stockReservedEventPublisher;
        this.reservedQuantity = reservedQuantity;
    }

    @Override
    public void fire() {
        stockReservedEventPublisher.publish(this);
    }
}