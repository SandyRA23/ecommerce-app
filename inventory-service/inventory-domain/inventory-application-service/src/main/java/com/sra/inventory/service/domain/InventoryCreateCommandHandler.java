package com.sra.inventory.service.domain;

import com.sra.inventory.service.domain.dto.create.CreateInventoryCommand;
import com.sra.inventory.service.domain.dto.create.CreateInventoryResponse;
import com.sra.inventory.service.domain.event.InventoryCreatedEvent;
import com.sra.inventory.service.domain.mapper.InventoryDataMapper;
import com.sra.inventory.service.domain.ports.output.message.publisher.InventoryMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InventoryCreateCommandHandler {
    private final InventoryCreateHelper inventoryCreateHelper;
    private final InventoryDataMapper inventoryDataMapper;
    private final InventoryMessagePublisher<InventoryCreatedEvent> inventoryMessagePublisher;

    public InventoryCreateCommandHandler(InventoryCreateHelper inventoryCreateHelper,
                                         InventoryDataMapper inventoryDataMapper,
                                         InventoryMessagePublisher<InventoryCreatedEvent> inventoryMessagePublisher) {
        this.inventoryCreateHelper = inventoryCreateHelper;
        this.inventoryDataMapper = inventoryDataMapper;
        this.inventoryMessagePublisher = inventoryMessagePublisher;
    }

    public CreateInventoryResponse createInventory(CreateInventoryCommand createInventoryCommand) {
        InventoryCreatedEvent inventoryCreatedEvent = inventoryCreateHelper.persistInventory(createInventoryCommand);
        log.info("Inventory is created with id: {}", inventoryCreatedEvent.getInventory().getId().getValue());
        inventoryMessagePublisher.publishEvent(inventoryCreatedEvent);

        return inventoryDataMapper.inventoryToCreateInventoryResponse(
                inventoryCreatedEvent.getInventory(),
                "Inventory created successfully"
        );
    }
}
