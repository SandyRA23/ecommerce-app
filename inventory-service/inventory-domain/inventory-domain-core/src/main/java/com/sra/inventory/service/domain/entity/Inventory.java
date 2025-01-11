package com.sra.inventory.service.domain.entity;

import com.sra.domain.entity.AggregateRoot;
import com.sra.domain.valueobject.*;
import com.sra.inventory.service.domain.exception.InventoryDomainException;
import com.sra.domain.valueobject.InventoryId;

import java.util.List;

public class Inventory extends AggregateRoot<InventoryId> {
    private final OrderId orderId;
    private final WarehouseId warehouseId;
    private final ProductId productId;
    private final List<InventoryItem> items;
    private InventoryStatus inventoryStatus;
    private List<String> failureMessages;

    public static final String FAILURE_MESSAGE_DELIMITER = ",";

    public void validateInventory() {
        validateInitialInventory();
        validateItemsStock();
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
        if (inventoryStatus != null || getId() != null) {
            throw new InventoryDomainException("Inventory is not in the correct state for initialization!");
        }
    }

    private void validateItemsStock() {
        items.forEach(this::validateItemStock);
    }

    private void validateItemStock(InventoryItem inventoryItem) {
        if (inventoryItem.getStock() < 0) {
            throw new InventoryDomainException("Inventory item stock cannot be negative for product " +
                    inventoryItem.getProduct());
        }
    }

    private Inventory(Builder builder) {
        super.setId(builder.inventoryId);
        orderId = builder.orderId;
        warehouseId = builder.warehouseId;
        productId = builder.productId;
        items = builder.items;
        inventoryStatus = builder.inventoryStatus;
        failureMessages = builder.failureMessages;
    }

    public static Builder builder() {
        return new Builder();
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public WarehouseId getWarehouseId() {
        return warehouseId;
    }

    public ProductId getProductId() {
        return productId;
    }

    public List<InventoryItem> getItems() {
        return items;
    }

    public InventoryStatus getInventoryStatus() {
        return inventoryStatus;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }

    public static final class Builder {
        private InventoryId inventoryId;
        private OrderId orderId;
        private WarehouseId warehouseId;
        private ProductId productId;
        private List<InventoryItem> items;
        private InventoryStatus inventoryStatus;
        private List<String> failureMessages;

        private Builder() {
        }

        public Builder inventoryId(InventoryId val) {
            inventoryId = val;
            return this;
        }

        public Builder orderId(OrderId val) {
            orderId = val;
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

        public Builder items(List<InventoryItem> val) {
            items = val;
            return this;
        }

        public Builder mutationStatus(InventoryStatus val) {
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
