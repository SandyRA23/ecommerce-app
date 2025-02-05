package com.sra.warehouse.service.domain.ports.input.service;

import com.sra.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.sra.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import jakarta.validation.Valid;

public interface WarehouseApplicationService {
    CreateWarehouseResponse createWarehouse(@Valid CreateWarehouseCommand createWarehouseCommand);
}
