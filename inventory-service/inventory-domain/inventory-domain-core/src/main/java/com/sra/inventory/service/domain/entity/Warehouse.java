package com.sra.inventory.service.domain.entity;

import com.sra.domain.entity.BaseEntity;
import com.sra.domain.valueobject.StreetAddress;
import com.sra.domain.valueobject.WarehouseId;

public class Warehouse extends BaseEntity<WarehouseId> {
    private String name;
    private int capacity;
    private int currentStockLevel;
    private StreetAddress warehouseAddress;


    public Warehouse(WarehouseId warehouseId, String name, StreetAddress warehouseAddress, int capacity, int currentStockLevel) {
        super.setId(warehouseId);
        this.name = name;
        this.warehouseAddress = warehouseAddress;
        this.capacity = capacity;
        this.currentStockLevel = currentStockLevel;
    }

    public Warehouse(WarehouseId warehouseId) {
        super.setId(warehouseId);
    }

    public String getName() {
        return name;
    }

    public StreetAddress getLocation() {
        return warehouseAddress;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentStockLevel() {
        return currentStockLevel;
    }
}
