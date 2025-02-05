package com.sra.warehouse.service.domain;

import com.sra.domain.event.publisher.DomainEventPublisher;
import com.sra.warehouse.service.domain.event.WarehouseCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationDomainEventPublisher implements
        ApplicationEventPublisherAware,
        DomainEventPublisher<WarehouseCreatedEvent> {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publish(WarehouseCreatedEvent domainEvent) {
        this.applicationEventPublisher.publishEvent(domainEvent);
        log.info("WarehouseCreatedEvent is published for warehouse id: {}", domainEvent.getWarehouse()
                .getId().getValue());
    }

}
