package com.sra.inventory.service.domain;

import com.sra.inventory.service.domain.dto.create.CreateInventoryCommand;
import com.sra.inventory.service.domain.entity.Inventory;
import com.sra.inventory.service.domain.entity.Product;
import com.sra.inventory.service.domain.entity.Warehouse;
import com.sra.inventory.service.domain.event.InventoryCreatedEvent;
import com.sra.inventory.service.domain.exception.InventoryDomainException;
import com.sra.inventory.service.domain.mapper.InventoryDataMapper;
import com.sra.inventory.service.domain.ports.output.message.publisher.InventoryMessagePublisher;
import com.sra.inventory.service.domain.ports.output.repository.InventoryRepository;
import com.sra.inventory.service.domain.ports.output.repository.ProductRepository;
import com.sra.inventory.service.domain.ports.output.repository.WarehouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class InventoryCreateHelper {
    private final InventoryDomainService inventoryDomainService;
    private final InventoryRepository inventoryRepository;
    private final WarehouseRepository warehouseRepository;
    private final ProductRepository productRepository;
    private final InventoryDataMapper inventoryDataMapper;
    private final InventoryMessagePublisher inventoryMessagePublisher;

    public InventoryCreateHelper(InventoryDomainService inventoryDomainService,
                                 InventoryRepository inventoryRepository,
                                 WarehouseRepository warehouseRepository,
                                 ProductRepository productRepository,
                                 InventoryDataMapper inventoryDataMapper,
                                 InventoryMessagePublisher inventoryMessagePublisher) {
        this.inventoryDomainService = inventoryDomainService;
        this.inventoryRepository = inventoryRepository;
        this.warehouseRepository = warehouseRepository;
        this.productRepository = productRepository;
        this.inventoryDataMapper = inventoryDataMapper;
        this.inventoryMessagePublisher = inventoryMessagePublisher;
    }

    @Transactional
    public InventoryCreatedEvent persistInventory(CreateInventoryCommand createInventoryCommand) {
        checkWarehouse(createInventoryCommand.getWarehouseId());
        checkProduct(createInventoryCommand.getProductId());

        Inventory inventory = inventoryDataMapper.createInventoryCommandToInventory(createInventoryCommand);

        int stockQuantity = inventory.getStockQuantity();
        int reservedQuantity = inventory.getReservedQuantity();

        InventoryCreatedEvent inventoryCreatedEvent = inventoryDomainService.validateAndInitiateInventory(
                inventory, stockQuantity, reservedQuantity, inventoryMessagePublisher);

        saveInventory(inventory);

        log.info("Inventory is created with id: {}", inventoryCreatedEvent.getInventory().getId().getValue());
        return inventoryCreatedEvent;
    }

    private void checkWarehouse(UUID warehouseId) {
        Optional<Warehouse> warehouse = warehouseRepository.findWarehouseById(warehouseId);
        if (warehouse.isEmpty()) {
            log.warn("Could not find warehouse with id: {}", warehouseId);
            throw new InventoryDomainException("Could not find warehouse with id: " + warehouseId);
        }
    }

    private void checkProduct(UUID productId) {
        Optional<Product> product = productRepository.findProductById(productId);
        if (product.isEmpty()) {
            log.warn("Could not find product with id: {}", productId);
            throw new InventoryDomainException("Could not find product with id: " + productId);
        }
    }

    private Inventory saveInventory(Inventory inventory) {
        Inventory inventoryResult = inventoryRepository.save(inventory);
        if (inventoryResult == null) {
            log.error("Could not save inventory!");
            throw new InventoryDomainException("Could not save inventory!");
        }
        log.info("Inventory is saved with id: {}", inventoryResult.getId().getValue());
        return inventoryResult;
    }
}
