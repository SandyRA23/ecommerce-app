package com.sra.inventory.service.domain.ports.output.message.publisher;

import com.sra.domain.event.DomainEvent;
import com.sra.domain.event.publisher.DomainEventPublisher;

public interface InventoryMessagePublisher<T extends DomainEvent> extends DomainEventPublisher<T> {
    void publishEvent(T event);
}
