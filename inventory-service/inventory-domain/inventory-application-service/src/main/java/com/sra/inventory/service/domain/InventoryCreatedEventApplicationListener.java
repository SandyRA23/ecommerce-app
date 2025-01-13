package com.sra.inventory.service.domain;

import com.sra.inventory.service.domain.event.InventoryCreatedEvent;
import com.sra.inventory.service.domain.ports.output.message.publisher.InventoryMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class InventoryCreatedEventApplicationListener {
    private final InventoryMessagePublisher inventoryMessagePublisher;

    public InventoryCreatedEventApplicationListener(InventoryMessagePublisher inventoryMessagePublisher){
        this.inventoryMessagePublisher = inventoryMessagePublisher;
    }

    @TransactionalEventListener
    void process(InventoryCreatedEvent inventoryCreatedEvent) {
        inventoryMessagePublisher.publish(inventoryCreatedEvent);
    }

}
