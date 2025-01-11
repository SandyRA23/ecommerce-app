package com.sra.inventory.service.domain;

import com.sra.domain.event.publisher.DomainEventPublisher;
import com.sra.inventory.service.domain.entity.Inventory;
import com.sra.inventory.service.domain.event.InventoryCancelledEvent;
import com.sra.inventory.service.domain.event.InventoryCreatedEvent;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static com.sra.domain.DomainConstants.UTC;

public class InventoryDomainServiceImpl implements InventoryDomainService {

    @Override
    public InventoryCreatedEvent validateAndInitiateInventory(Inventory inventory, DomainEventPublisher<InventoryCreatedEvent> inventoryCreatedEventDomainEventPublisher){
        inventory.validateInventory();
        //log.info("Inventory with id: {} is initiated", inventory.getId().getValue());
        return new InventoryCreatedEvent(inventory, ZonedDateTime.now(ZoneId.of(UTC)), inventoryCreatedEventDomainEventPublisher);
    }

    @Override
    public InventoryCreatedEvent stockMutation(Inventory inventory, DomainEventPublisher<InventoryCreatedEvent> inventoryCreatedEventDomainEventPublisher){
        inventory.stockMutation();
        return new InventoryCreatedEvent(inventory, ZonedDateTime.now(ZoneId.of(UTC)), inventoryCreatedEventDomainEventPublisher);
    }

    @Override
    public InventoryCancelledEvent cancelStockMutation(Inventory inventory, List<String> failureMessages, DomainEventPublisher<InventoryCancelledEvent> inventoryCancelledEventDomainEventPublisher){
        inventory.initCancelStockMutation(failureMessages);
        inventory.cancelStockMutation(failureMessages);
        return new InventoryCancelledEvent(inventory, ZonedDateTime.now(ZoneId.of(UTC)), inventoryCancelledEventDomainEventPublisher);
    }
}
