package com.sra.warehouse.service.domain.entity;

import com.sra.domain.entity.BaseEntity;
import com.sra.domain.valueobject.InventoryId;
import com.sra.domain.valueobject.ProductId;
import com.sra.domain.valueobject.WarehouseId;

public class Inventory extends BaseEntity<InventoryId> {
    private WarehouseId warehouseId;
    private ProductId productId;
    private int quantity;

    public Inventory(InventoryId inventoryId, WarehouseId warehouseId, ProductId productId, int quantity) {
        super.setId(inventoryId);
        this.warehouseId = warehouseId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Inventory(InventoryId inventoryId) {

        super.setId(inventoryId);
    }

    public WarehouseId getWarehouseId() {
        return warehouseId;
    }

    public ProductId getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
