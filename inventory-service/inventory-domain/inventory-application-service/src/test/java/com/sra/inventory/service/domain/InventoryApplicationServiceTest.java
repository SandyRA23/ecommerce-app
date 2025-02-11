package com.sra.inventory.service.domain;

import com.sra.domain.valueobject.InventoryId;
import com.sra.domain.valueobject.InventoryStatus;
import com.sra.domain.valueobject.ProductId;
import com.sra.domain.valueobject.WarehouseId;
import com.sra.inventory.service.domain.dto.create.CreateInventoryCommand;
import com.sra.inventory.service.domain.dto.create.CreateInventoryResponse;
import com.sra.inventory.service.domain.entity.Inventory;
import com.sra.inventory.service.domain.mapper.InventoryDataMapper;
import com.sra.inventory.service.domain.ports.input.service.InventoryApplicationService;
import com.sra.inventory.service.domain.ports.output.repository.InventoryRepository;
import com.sra.inventory.service.domain.ports.output.repository.ProductRepository;
import com.sra.inventory.service.domain.ports.output.repository.WarehouseRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = InventoryTestConfiguration.class)
public class InventoryApplicationServiceTest {
    @Autowired
    private InventoryApplicationService inventoryApplicationService;

    @Autowired
    private InventoryDataMapper inventoryDataMapper;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProductRepository productRepository;

    private CreateInventoryCommand createInventoryCommand;

    private CreateInventoryCommand createInventoryCommandWrongReservedQuantity;

    private final WarehouseId WAREHOUSE_ID = new WarehouseId(UUID.fromString("bf24105c-271f-4f6d-9fd3-efeee7c44ea9"));
    private final ProductId PRODUCT_ID = new ProductId(UUID.fromString("c551f8c1-7f59-4a8a-b18e-c17020b25827"));
    private final UUID INVENTORY_ID = UUID.fromString("78d408cd-e695-4730-8850-ccd43624f175");


    private final int STOCK = 500;

    private final int RESERVED = 300;

    @BeforeAll
    public void init(){
        createInventoryCommand = CreateInventoryCommand.builder()
                .productId(PRODUCT_ID.getValue())
                .warehouseId(WAREHOUSE_ID.getValue())
                .stockQuantity(STOCK)
                .reservedQuantity(RESERVED)
                .build();

        createInventoryCommandWrongReservedQuantity = CreateInventoryCommand.builder()
                .productId(PRODUCT_ID.getValue())
                .warehouseId(WAREHOUSE_ID.getValue())
                .stockQuantity(STOCK)
                .reservedQuantity(STOCK + 1)  // Reserved quantity more than stock
                .build();


        Inventory inventory = inventoryDataMapper.createInventoryCommandToInventory(createInventoryCommand);
        inventory.setId(new InventoryId(INVENTORY_ID));

        when(warehouseRepository.findWarehouseById(WAREHOUSE_ID.getValue())).thenReturn(Optional.of(new Warehouse(WAREHOUSE_ID)));
        when(productRepository.findProductById(PRODUCT_ID.getValue())).thenReturn(Optional.of(new Product(PRODUCT_ID)));
        when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventory);

    }

    @Test
    public void testCreateInventory() {
        CreateInventoryResponse createInventoryResponse = inventoryApplicationService.createInventory(createInventoryCommand);
        assertEquals(createInventoryResponse.getInventoryStatus(), InventoryStatus.PENDING);
        assertEquals(createInventoryResponse.getMessage(), "Inventory created successfully");
        assertNotNull(createInventoryResponse.getWarehouseId());
        assertNotNull(createInventoryResponse.getProductId());

    }

//    @Test
//    public void testCreateInventoryWithWrongReservedQuantity() {
//        InventoryDomainException inventoryDomainException = assertThrows(InventoryDomainException.class,
//                () -> inventoryApplicationService.createInventory(createInventoryCommandWrongReservedQuantity));
//        assertEquals(inventoryDomainException.getMessage(), "Reserved quantity more than stock available!");
//    }

}
