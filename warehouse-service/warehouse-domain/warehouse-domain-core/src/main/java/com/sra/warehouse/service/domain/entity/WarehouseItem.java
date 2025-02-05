package com.sra.warehouse.service.domain.entity;

import com.sra.domain.entity.BaseEntity;
import com.sra.domain.valueobject.ProductId;
import com.sra.domain.valueobject.WarehouseId;
import com.sra.warehouse.service.domain.valueobject.WarehouseItemId;

public class WarehouseItem extends BaseEntity<WarehouseItemId> {
    private final WarehouseId warehouseId;
    private final ProductId productId;
    private final int quantity;

    public WarehouseItem(WarehouseItemId warehouseItemId, WarehouseId warehouseId, ProductId productId, int quantity) {
        super.setId(warehouseItemId);
        this.warehouseId = warehouseId;
        this.productId = productId;
        this.quantity = quantity;
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

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private WarehouseItemId warehouseItemId;
        private WarehouseId warehouseId;
        private ProductId productId;
        private int quantity;

        private Builder() {}

        public Builder warehouseItemId(WarehouseItemId warehouseItemId) {
            this.warehouseItemId = warehouseItemId;
            return this;
        }

        public Builder warehouseId(WarehouseId warehouseId) {
            this.warehouseId = warehouseId;
            return this;
        }

        public Builder productId(ProductId productId) {
            this.productId = productId;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public WarehouseItem build() {
            return new WarehouseItem(warehouseItemId, warehouseId, productId, quantity);
        }

    }
}