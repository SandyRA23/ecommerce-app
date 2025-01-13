package com.sra.inventory.service.domain;

import com.sra.domain.event.publisher.DomainEventPublisher;
import com.sra.inventory.service.domain.event.InventoryCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationDomainEventPublisher implements
        ApplicationEventPublisherAware,
        DomainEventPublisher<InventoryCreatedEvent> {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publish(InventoryCreatedEvent domainEvent) {
        this.applicationEventPublisher.publishEvent(domainEvent);
        log.info("InventoryCreatedEvent is published for inventory id: {}", domainEvent.getInventory()
                .getId().getValue());
    }
}
