package com.sra.inventory.service.domain.ports.output.message.publisher;

import com.sra.domain.event.publisher.DomainEventPublisher;
import com.sra.inventory.service.domain.event.InventoryCreatedEvent;
import com.sra.inventory.service.domain.event.StockMutationCreatedEvent;

public interface InventoryMessagePublisher extends DomainEventPublisher<InventoryCreatedEvent> {
    void publishInventoryCreatedEvent(InventoryCreatedEvent event);
    void publishStockMutationEvent(StockMutationCreatedEvent event);
}
