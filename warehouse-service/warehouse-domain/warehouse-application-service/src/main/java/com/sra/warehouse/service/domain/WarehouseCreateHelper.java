package com.sra.warehouse.service.domain;

import com.sra.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.sra.warehouse.service.domain.entity.Warehouse;
import com.sra.warehouse.service.domain.event.WarehouseCreatedEvent;
import com.sra.warehouse.service.domain.exception.WarehouseDomainException;
import com.sra.warehouse.service.domain.mapper.WarehouseDataMapper;
import com.sra.warehouse.service.domain.ports.output.message.publisher.WarehouseCreatedRequestMessagePublisher;
import com.sra.warehouse.service.domain.ports.output.repository.WarehouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class WarehouseCreateHelper {

    private final WarehouseDomainService warehouseDomainService;

    private final WarehouseRepository warehouseRepository;

    private final WarehouseDataMapper warehouseDataMapper;

    private final WarehouseCreatedRequestMessagePublisher warehouseCreatedEventDomainEventPublisher;

    public WarehouseCreateHelper(WarehouseDomainService warehouseDomainService,
                             WarehouseRepository warehouseRepository,
                             WarehouseDataMapper warehouseDataMapper,
                             WarehouseCreatedRequestMessagePublisher warehouseCreatedEventDomainEventPublisher) {
        this.warehouseDomainService = warehouseDomainService;
        this.warehouseRepository = warehouseRepository;
        this.warehouseDataMapper = warehouseDataMapper;
        this.warehouseCreatedEventDomainEventPublisher = warehouseCreatedEventDomainEventPublisher;
    }

    @Transactional
    public WarehouseCreatedEvent persistWarehouse(CreateWarehouseCommand createWarehouseCommand) {
        Warehouse warehouse = warehouseDataMapper.createWarehouseCommandToWarehouse(createWarehouseCommand);
        WarehouseCreatedEvent warehouseCreatedEvent = warehouseDomainService.validateAndInitializeWarehouse(warehouse,
                warehouseCreatedEventDomainEventPublisher);
        saveWarehouse(warehouse);
        log.info("Warehouse is created with id: {}", warehouseCreatedEvent.getWarehouse().getId().getValue());
        return warehouseCreatedEvent;
    }

    private Warehouse saveWarehouse(Warehouse warehouse) {
        Warehouse warehouseResult = warehouseRepository.save(warehouse);
        if (warehouseResult == null) {
            log.error("Could not save warehouse!");
            throw new WarehouseDomainException("Could not save warehouse!");
        }
        log.info("Warehouse is saved with id: {}", warehouseResult.getId().getValue());
        return warehouseResult;
    }
}