package com.sra.inventory.service.domain;

import com.sra.domain.event.DomainEvent;
import com.sra.domain.event.publisher.DomainEventPublisher;
import com.sra.inventory.service.domain.event.InventoryCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApplicationDomainEventPublisher<T extends DomainEvent> implements ApplicationEventPublisherAware, DomainEventPublisher<T> {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publish(T domainEvent) {
        this.applicationEventPublisher.publishEvent(domainEvent);
        log.info("{} is published", domainEvent.getClass().getSimpleName());
    }
}
