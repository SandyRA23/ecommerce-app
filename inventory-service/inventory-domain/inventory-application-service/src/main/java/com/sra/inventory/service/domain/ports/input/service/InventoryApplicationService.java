package com.sra.inventory.service.domain.ports.input.service;

import com.sra.inventory.service.domain.dto.create.CreateInventoryCommand;
import com.sra.inventory.service.domain.dto.create.CreateInventoryResponse;
import jakarta.validation.Valid;

public interface InventoryApplicationService {
    CreateInventoryResponse createInventory(@Valid CreateInventoryCommand createInventoryCommand);
}
