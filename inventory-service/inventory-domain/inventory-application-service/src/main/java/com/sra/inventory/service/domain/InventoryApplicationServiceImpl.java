package com.sra.inventory.service.domain;

import com.sra.inventory.service.domain.dto.create.CreateInventoryCommand;
import com.sra.inventory.service.domain.dto.create.CreateInventoryResponse;
import com.sra.inventory.service.domain.ports.input.service.InventoryApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class InventoryApplicationServiceImpl implements InventoryApplicationService {
    private final InventoryCreateCommandHandler inventoryCreateCommandHandler;

    InventoryApplicationServiceImpl(InventoryCreateCommandHandler inventoryCreateCommandHandler){
        this.inventoryCreateCommandHandler = inventoryCreateCommandHandler;
    }

    @Override
    public CreateInventoryResponse createInventory(CreateInventoryCommand createInventoryCommand) {
        return inventoryCreateCommandHandler.createInventory(createInventoryCommand);
    }
}
