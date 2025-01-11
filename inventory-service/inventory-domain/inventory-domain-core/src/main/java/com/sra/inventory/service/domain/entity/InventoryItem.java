package com.sra.inventory.service.domain.entity;

import com.sra.domain.entity.BaseEntity;
import com.sra.domain.valueobject.InventoryId;
import com.sra.inventory.service.domain.valueobject.InventoryItemId;

public class InventoryItem extends BaseEntity<InventoryItemId> {
    private InventoryId inventoryId;
    private final Product product;
    private final Warehouse warehouse;
    private int stock;
    private int reservedStock;

    void initializeInventoryItem(InventoryId inventoryId, InventoryItemId inventoryItemId) {
        this.inventoryId = inventoryId;
        super.setId(inventoryItemId);
    }

    boolean isStockValid() {
        return stock >= 0 && reservedStock >= 0 && reservedStock <= stock;
    }

    private InventoryItem(Builder builder) {
        super.setId(builder.inventoryItemId);
        product = builder.product;
        warehouse = builder.warehouse;
        stock = builder.stock;
        reservedStock = builder.reservedStock;
    }

    public static Builder builder() {
        return new Builder();
    }

    public InventoryId getInventoryId() {
        return inventoryId;
    }

    public Product getProduct() {
        return product;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getReservedStock() {
        return reservedStock;
    }

    public void setReservedStock(int reservedStock) {
        this.reservedStock = reservedStock;
    }

    public static final class Builder {
        private InventoryItemId inventoryItemId;
        private Product product;
        private Warehouse warehouse;
        private int stock;
        private int reservedStock;

        private Builder() {
        }

        public Builder inventoryItemId(InventoryItemId val) {
            inventoryItemId = val;
            return this;
        }

        public Builder product(Product val) {
            product = val;
            return this;
        }

        public Builder warehouse(Warehouse val) {
            warehouse = val;
            return this;
        }

        public Builder stock(int val) {
            stock = val;
            return this;
        }

        public Builder reservedStock(int val) {
            reservedStock = val;
            return this;
        }

        public InventoryItem build() {
            return new InventoryItem(this);
        }
    }
}
