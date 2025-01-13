package com.sra.inventory.service.domain.mapper;

import com.sra.domain.valueobject.InventoryStatus;
import com.sra.domain.valueobject.ProductId;
import com.sra.domain.valueobject.WarehouseId;
import com.sra.inventory.service.domain.dto.create.CreateInventoryCommand;
import com.sra.inventory.service.domain.dto.create.CreateInventoryResponse;
import com.sra.inventory.service.domain.entity.Inventory;
import com.sra.inventory.service.domain.entity.InventoryItem;
import com.sra.inventory.service.domain.entity.Product;
import com.sra.inventory.service.domain.entity.Warehouse;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InventoryDataMapper {

    public Inventory createInventoryCommandToInventory(CreateInventoryCommand createInventoryCommand) {
        return Inventory.builder()
                .productId(new ProductId(createInventoryCommand.getProductId()))
                .warehouseId(new WarehouseId(createInventoryCommand.getWarehouseId()))
                .stockQuantity(createInventoryCommand.getStockQuantity())
                .reservedQuantity(createInventoryCommand.getReservedQuantity())
                .inventoryStatus(InventoryStatus.CREATED)
                .build();
    }

    public CreateInventoryResponse inventoryToCreateInventoryResponse(Inventory inventory, String message) {
        return CreateInventoryResponse.builder()
                .inventoryId(inventory.getId().getValue())
                .warehouseId(inventory.getWarehouseId().getValue())
                .productId(inventory.getProductId().getValue())
                .inventoryStatus(inventory.getInventoryStatus().toString())
                .message(message)
                .build();
    }
}
