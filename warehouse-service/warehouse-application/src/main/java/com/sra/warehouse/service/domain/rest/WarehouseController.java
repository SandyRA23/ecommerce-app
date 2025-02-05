package com.sra.warehouse.service.domain.rest;

import com.sra.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.sra.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.sra.warehouse.service.domain.ports.input.service.WarehouseApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/warehouses", produces = "application/vnd.api.v1+json")
public class WarehouseController {

    private final WarehouseApplicationService warehouseApplicationService;

    public WarehouseController(WarehouseApplicationService warehouseApplicationService) {
        this.warehouseApplicationService = warehouseApplicationService;
    }

    @PostMapping
    public ResponseEntity<CreateWarehouseResponse> createWarehouse(@RequestBody CreateWarehouseCommand createWarehouseCommand) {
        log.info("Creating warehouse for name: {} at location: {}", createWarehouseCommand.getName(),
                createWarehouseCommand.getLocation());
        CreateWarehouseResponse createWarehouseResponse = warehouseApplicationService.createWarehouse(createWarehouseCommand);
        log.info("Warehouse created with id: {}", createWarehouseResponse.getWarehouseId());
        return ResponseEntity.ok(createWarehouseResponse);
    }
}