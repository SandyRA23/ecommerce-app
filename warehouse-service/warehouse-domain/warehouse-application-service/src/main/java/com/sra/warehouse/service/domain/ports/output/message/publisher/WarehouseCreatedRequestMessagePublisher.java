package com.sra.warehouse.service.domain.ports.output.message.publisher;

import com.sra.domain.event.publisher.DomainEventPublisher;
import com.sra.warehouse.service.domain.event.WarehouseCreatedEvent;

public interface WarehouseCreatedRequestMessagePublisher extends DomainEventPublisher<WarehouseCreatedEvent> {
}
