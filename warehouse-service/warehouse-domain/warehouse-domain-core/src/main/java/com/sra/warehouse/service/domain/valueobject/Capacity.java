package com.sra.warehouse.service.domain.valueobject;

public class Capacity {
    private final int maxCapacity;
    private final int currentCapacity;

    public Capacity(int maxCapacity, int currentCapacity) {
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
    }

    public boolean isValid() {
        return currentCapacity <= maxCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }
}
