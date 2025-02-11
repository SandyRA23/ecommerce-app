package com.sra.inventory.service.domain.entity;

import com.sra.domain.entity.AggregateRoot;
import com.sra.domain.valueobject.*;
import com.sra.domain.valueobject.InventoryId;

import java.util.List;

public class Inventory extends AggregateRoot<InventoryId> {

    private final ProductId productId;
    private WarehouseId warehouseId;
    private int stockQuantity;
    private int reservedQuantity;
    private InventoryStatus inventoryStatus;

    private Inventory(Builder builder) {
        super.setId(builder.inventoryId);
        this.productId = builder.productId;
        this.warehouseId = builder.warehouseId;
        this.stockQuantity = builder.stockQuantity;
        this.reservedQuantity = builder.reservedQuantity;
        this.inventoryStatus = builder.inventoryStatus;
    }

    public ProductId getProductId() {
        return productId;
    }

    public WarehouseId getWarehouseId() {
        return warehouseId;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public int getReservedQuantity() {
        return reservedQuantity;
    }

    public InventoryStatus getInventoryStatus() {
        return inventoryStatus;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setReservedQuantity(int reservedQuantity) {
        this.reservedQuantity = reservedQuantity;
    }

    public void setInventoryStatus(InventoryStatus inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public void validateInventory() {
        if (stockQuantity < 0 || reservedQuantity < 0) {
            throw new IllegalStateException("Stock quantity and reserved quantity cannot be negative.");
        }
    }

    public void reserveStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Reserved quantity must be greater than zero.");
        }
        if (quantity > stockQuantity) {
            throw new IllegalStateException("Not enough stock available to reserve.");
        }
        this.stockQuantity -= quantity;
        this.reservedQuantity += quantity;
        this.inventoryStatus = InventoryStatus.RESERVED;
    }

    public void releaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Released quantity must be greater than zero.");
        }
        if (quantity > reservedQuantity) {
            throw new IllegalStateException("Not enough reserved stock to release.");
        }
        this.reservedQuantity -= quantity;
        this.stockQuantity += quantity;
        this.inventoryStatus = InventoryStatus.AVAILABLE;
    }

    public void failStockReservation(List<String> failureMessages) {
        if (!failureMessages.isEmpty()) {
            this.inventoryStatus = InventoryStatus.FAILED;
        }
    }

    public void mutateStock(WarehouseId sourceWarehouseId, WarehouseId destinationWarehouseId, int movedQuantity) {
        if (movedQuantity <= 0) {
            throw new IllegalArgumentException("Mutated quantity must be greater than zero.");
        }
        if (!this.warehouseId.equals(sourceWarehouseId)) {
            throw new IllegalStateException("Inventory is not in the source warehouse.");
        }
        if (this.stockQuantity < movedQuantity) {
            throw new IllegalStateException("Not enough stock to mutate.");
        }

        this.stockQuantity -= movedQuantity;

        this.warehouseId = destinationWarehouseId;

        this.inventoryStatus = InventoryStatus.MUTATED;
    }

    public static class Builder {
        private InventoryId inventoryId;
        private ProductId productId;
        private WarehouseId warehouseId;
        private int stockQuantity;
        private int reservedQuantity;
        private InventoryStatus inventoryStatus;

        public Builder inventoryId(InventoryId inventoryId) {
            this.inventoryId = inventoryId;
            return this;
        }

        public Builder productId(ProductId productId) {
            this.productId = productId;
            return this;
        }

        public Builder warehouseId(WarehouseId warehouseId) {
            this.warehouseId = warehouseId;
            return this;
        }

        public Builder stockQuantity(int stockQuantity) {
            this.stockQuantity = stockQuantity;
            return this;
        }

        public Builder reservedQuantity(int reservedQuantity) {
            this.reservedQuantity = reservedQuantity;
            return this;
        }

        public Builder inventoryStatus(InventoryStatus inventoryStatus) {
            this.inventoryStatus = inventoryStatus;
            return this;
        }

        public Inventory build() {
            Inventory inventory = new Inventory(this);
            inventory.setStockQuantity(stockQuantity);
            inventory.setReservedQuantity(reservedQuantity);
            inventory.setInventoryStatus(InventoryStatus.CREATED);
            return inventory;
        }
    }
}
