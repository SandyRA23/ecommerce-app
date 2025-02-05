package com.sra.warehouse.service.domain;

import com.sra.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.sra.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.sra.warehouse.service.domain.ports.input.service.WarehouseApplicationService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
class WarehouseApplicationServiceImpl implements WarehouseApplicationService {

    private final WarehouseCreateCommandHandler warehouseCreateCommandHandler;

    WarehouseApplicationServiceImpl(WarehouseCreateCommandHandler warehouseCreateCommandHandler) {
        this.warehouseCreateCommandHandler = warehouseCreateCommandHandler;
    }

    @Override
    public CreateWarehouseResponse createWarehouse(CreateWarehouseCommand createWarehouseCommand) {
        return warehouseCreateCommandHandler.createWarehouse(createWarehouseCommand);
    }
}
