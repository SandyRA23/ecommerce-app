package com.sra.warehouse.service.domain;

import com.sra.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.sra.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.sra.warehouse.service.domain.event.WarehouseCreatedEvent;
import com.sra.warehouse.service.domain.mapper.WarehouseDataMapper;
import com.sra.warehouse.service.domain.ports.output.message.publisher.WarehouseCreatedRequestMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WarehouseCreateCommandHandler {

    private final WarehouseCreateHelper warehouseCreateHelper;

    private final WarehouseDataMapper warehouseDataMapper;

    private final WarehouseCreatedRequestMessagePublisher warehouseCreatedRequestMessagePublisher;

    public WarehouseCreateCommandHandler(WarehouseCreateHelper warehouseCreateHelper,
                                     WarehouseDataMapper warehouseDataMapper,
                                     WarehouseCreatedRequestMessagePublisher warehouseCreatedRequestMessagePublisher) {
        this.warehouseCreateHelper = warehouseCreateHelper;
        this.warehouseDataMapper = warehouseDataMapper;
        this.warehouseCreatedRequestMessagePublisher = warehouseCreatedRequestMessagePublisher;
    }

    public CreateWarehouseResponse createWarehouse(CreateWarehouseCommand createWarehouseCommand) {
        WarehouseCreatedEvent warehouseCreatedEvent = warehouseCreateHelper.persistWarehouse(createWarehouseCommand);
        log.info("Warehouse is created with id: {}", warehouseCreatedEvent.getWarehouse().getId().getValue());
        warehouseCreatedRequestMessagePublisher.publish(warehouseCreatedEvent);
        return warehouseDataMapper.warehouseToCreateWarehouseResponse(warehouseCreatedEvent.getWarehouse());
    }
}