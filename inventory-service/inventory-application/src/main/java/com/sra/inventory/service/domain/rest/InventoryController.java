package com.sra.inventory.service.domain.rest;

import com.sra.inventory.service.domain.dto.create.CreateInventoryCommand;
import com.sra.inventory.service.domain.dto.create.CreateInventoryResponse;
import com.sra.inventory.service.domain.ports.input.service.InventoryApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/inventories", produces = "application/vnd.api.v1+json")
public class InventoryController {

    private final InventoryApplicationService inventoryApplicationService;

    public InventoryController(InventoryApplicationService inventoryApplicationService) {
        this.inventoryApplicationService = inventoryApplicationService;
    }

    @PostMapping
    public ResponseEntity<CreateInventoryResponse> createInventory(@RequestBody CreateInventoryCommand createInventoryCommand) {
        log.info("Creating inventory for productId: {} with quantity: {} at warehouseId: {}", createInventoryCommand.getProductId(),
                createInventoryCommand.getStockQuantity(), createInventoryCommand.getWarehouseId());
        CreateInventoryResponse createInventoryResponse = inventoryApplicationService.createInventory(createInventoryCommand);
        log.info("Inventory created with id: {}", createInventoryResponse.getInventoryId());
        return ResponseEntity.ok(createInventoryResponse);
    }
}