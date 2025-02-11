package com.sra.inventory.service.domain.event;

import com.sra.domain.event.publisher.DomainEventPublisher;
import com.sra.domain.valueobject.WarehouseId;
import com.sra.inventory.service.domain.entity.Inventory;

import java.time.ZonedDateTime;

public class StockMutatedEvent extends InventoryEvent {
    private final DomainEventPublisher<StockMutatedEvent> stockMutatedEventPublisher;
    private final WarehouseId fromWarehouse;
    private final WarehouseId toWarehouse;
    private final int movedQuantity;

    public StockMutatedEvent(Inventory inventory,
                             ZonedDateTime createdAt,
                             WarehouseId fromWarehouse,
                             WarehouseId toWarehouse,
                             DomainEventPublisher<StockMutatedEvent> stockMutatedEventPublisher,
                             int movedQuantity ) {
        super(inventory, createdAt);
        this.stockMutatedEventPublisher = stockMutatedEventPublisher;
        this.fromWarehouse = fromWarehouse;
        this.toWarehouse = toWarehouse;
        this.movedQuantity  = movedQuantity ;
    }

    @Override
    public void fire() {
        stockMutatedEventPublisher.publish(this);
    }
}