package com.sra.inventory.service.domain.entity;

import com.sra.domain.entity.AggregateRoot;
import com.sra.domain.valueobject.*;
import com.sra.inventory.service.domain.exception.InventoryDomainException;
import com.sra.domain.valueobject.InventoryId;

import java.util.List;

public class Inventory extends AggregateRoot<InventoryId> {
    private final WarehouseId warehouseId;
    private final ProductId productId;
    private int stockQuantity;
    private int reservedQuantity;
    private InventoryStatus inventoryStatus;
    private List<String> failureMessages;

    public static final String FAILURE_MESSAGE_DELIMITER = ",";

    public void validateInventory() {
        validateInitialInventory();
        validateStockQuantities();
    }

    public void stockMutation() {
        if (inventoryStatus != InventoryStatus.APPROVED) {
            throw new InventoryDomainException("Inventory is not in the correct state for stock mutation!");
        }
        inventoryStatus = InventoryStatus.MUTATING;
    }

    public void initCancelStockMutation(List<String> failureMessages) {
        if (inventoryStatus != InventoryStatus.MUTATING) {
            throw new InventoryDomainException("Inventory is not in the correct state for initCancel stock mutation!");
        }
        inventoryStatus = InventoryStatus.CANCELLING;
        updateFailureMessages(failureMessages);
    }

    public void cancelStockMutation(List<String> failureMessages) {
        if (!(inventoryStatus == InventoryStatus.MUTATING || inventoryStatus == InventoryStatus.PENDING)) {
            throw new InventoryDomainException("Inventory is not in the correct state for canceling stock mutation!");
        }
        inventoryStatus = InventoryStatus.CANCELLED;
        updateFailureMessages(failureMessages);
    }

    private void updateFailureMessages(List<String> failureMessages) {
        if (this.failureMessages != null && failureMessages != null) {
            this.failureMessages.addAll(failureMessages.stream().filter(message -> !message.isEmpty()).toList());
        }
        if (this.failureMessages == null) {
            this.failureMessages = failureMessages;
        }
    }

    private void validateInitialInventory() {
        if (inventoryStatus == null || getId() == null) {
            throw new InventoryDomainException("Inventory is not in the correct state for initialization!");
        }
    }

    private void validateStockQuantities() {
        if (stockQuantity < 0) {
            throw new InventoryDomainException("Stock quantity cannot be negative!");
        }
        if (reservedQuantity < 0) {
            throw new InventoryDomainException("Reserved quantity cannot be negative!");
        }
        if (reservedQuantity > stockQuantity) {
            throw new InventoryDomainException("Reserved quantity cannot exceed available stock!");
        }
    }

    private Inventory(Builder builder) {
        super.setId(builder.inventoryId);
        warehouseId = builder.warehouseId;
        productId = builder.productId;
        this.stockQuantity = builder.stockQuantity;
        this.reservedQuantity = builder.reservedQuantity;
        inventoryStatus = builder.inventoryStatus;
        failureMessages = builder.failureMessages;
    }

    public static Builder builder() {
        return new Builder();
    }

    public WarehouseId getWarehouseId() {
        return warehouseId;
    }

    public ProductId getProductId() {
        return productId;
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

    public List<String> getFailureMessages() {
        return failureMessages;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0) {
            throw new InventoryDomainException("Stock quantity cannot be negative!");
        }
        this.stockQuantity = stockQuantity;
    }

    public void setReservedQuantity(int reservedQuantity) {
        if (reservedQuantity < 0) {
            throw new InventoryDomainException("Reserved quantity cannot be negative!");
        }
        if (reservedQuantity > this.stockQuantity) {
            throw new InventoryDomainException("Reserved quantity cannot exceed available stock!");
        }
        this.reservedQuantity = reservedQuantity;
    }

    public void setInventoryStatus(InventoryStatus inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public static final class Builder {
        private InventoryId inventoryId;
        private WarehouseId warehouseId;
        private ProductId productId;
        private int stockQuantity;
        private int reservedQuantity;
        private InventoryStatus inventoryStatus;
        private List<String> failureMessages;

        private Builder() {
        }

        public Builder inventoryId(InventoryId val) {
            inventoryId = val;
            return this;
        }

        public Builder warehouseId(WarehouseId val) {
            warehouseId = val;
            return this;
        }

        public Builder productId(ProductId val) {
            productId = val;
            return this;
        }

        public Builder stockQuantity(int val) {
            stockQuantity = val;
            return this;
        }

        public Builder reservedQuantity(int val) {
            reservedQuantity = val;
            return this;
        }

        public Builder inventoryStatus(InventoryStatus val) {
            inventoryStatus = val;
            return this;
        }

        public Builder failureMessages(List<String> val) {
            failureMessages = val;
            return this;
        }

        public Inventory build() {
            return new Inventory(this);
        }
    }
}
