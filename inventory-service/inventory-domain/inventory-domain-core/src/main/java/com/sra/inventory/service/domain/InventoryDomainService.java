package com.sra.inventory.service.domain;

import com.sra.domain.event.publisher.DomainEventPublisher;
import com.sra.inventory.service.domain.entity.Inventory;
import com.sra.inventory.service.domain.event.InventoryCancelledEvent;
import com.sra.inventory.service.domain.event.InventoryCreatedEvent;

import java.util.List;

public interface InventoryDomainService {
    InventoryCreatedEvent validateAndInitiateInventory(
            Inventory inventory,
            int stockQuantity,
            int reservedQuantity,
            DomainEventPublisher<InventoryCreatedEvent> inventoryCreatedEventDomainEventPublisher
    );

    InventoryCreatedEvent stockMutation(
            Inventory inventory,
            DomainEventPublisher<InventoryCreatedEvent> inventoryCreatedEventDomainEventPublisher
    );

    InventoryCancelledEvent cancelStockMutation(
            Inventory inventory,
            List<String> failureMessages,
            DomainEventPublisher<InventoryCancelledEvent> inventoryCancelledEventDomainEventPublisher
    );
}
