package com.sra.warehouse.service.domain.entity;

import com.sra.domain.entity.AggregateRoot;
import com.sra.domain.valueobject.WarehouseId;
import com.sra.warehouse.service.domain.exception.WarehouseDomainException;
import com.sra.warehouse.service.domain.valueobject.Capacity;
import com.sra.warehouse.service.domain.valueobject.Location;
import com.sra.warehouse.service.domain.valueobject.WarehouseStatus;

import java.util.List;
import java.util.UUID;

public class Warehouse extends AggregateRoot<WarehouseId> {
    private final String name;
    private final Location location;
    private final List<WarehouseItem> items;
    private final Capacity capacity;

    private WarehouseStatus warehouseStatus;
    private List<String> operationalIssues;

    public static final String ISSUE_DELIMITER = ",";

    public void initializeWarehouse() {
        setId(new WarehouseId(UUID.randomUUID()));
        warehouseStatus = WarehouseStatus.OPERATIONAL;
    }

    public void validateWarehouse() {
        validateInitialWarehouse();
        validateCapacity();
        validateItems();
    }

    public void activate() {
        if (warehouseStatus != WarehouseStatus.OPERATIONAL) {
            throw new WarehouseDomainException("Warehouse is not in correct state for activation!");
        }
        warehouseStatus = WarehouseStatus.ACTIVE;
    }

    public void deactivate() {
        if (warehouseStatus != WarehouseStatus.ACTIVE) {
            throw new WarehouseDomainException("Warehouse is not in correct state for deactivation!");
        }
        warehouseStatus = WarehouseStatus.INACTIVE;
    }

    public void reportIssue(List<String> issues) {
        if (warehouseStatus == WarehouseStatus.CLOSED) {
            throw new WarehouseDomainException("Warehouse is closed and cannot report issues!");
        }
        updateOperationalIssues(issues);
    }

    public void close() {
        if (warehouseStatus != WarehouseStatus.INACTIVE) {
            throw new WarehouseDomainException("Warehouse is not in correct state for closure!");
        }
        warehouseStatus = WarehouseStatus.CLOSED;
    }

    private void updateOperationalIssues(List<String> issues) {
        if (this.operationalIssues != null && issues != null) {
            this.operationalIssues.addAll(issues.stream().filter(issue -> !issue.isEmpty()).toList());
        }
        if (this.operationalIssues == null) {
            this.operationalIssues = issues;
        }
    }

    private void validateInitialWarehouse() {
        if (warehouseStatus != null || getId() != null) {
            throw new WarehouseDomainException("Warehouse is not in correct state for initialization!");
        }
    }

    private void validateCapacity() {
        if (capacity == null || !capacity.isValid()) {
            throw new WarehouseDomainException("Warehouse capacity is invalid!");
        }
    }

    private void validateItems() {
        int totalItems = items.stream().mapToInt(WarehouseItem::getQuantity).sum();
        if (totalItems > capacity.getMaxCapacity()) {
            throw new WarehouseDomainException("Total items exceed warehouse capacity!");
        }
    }

    public void setWarehouseStatus(WarehouseStatus warehouseStatus) {
        this.warehouseStatus = warehouseStatus;
    }

    private Warehouse(Builder builder) {
        super.setId(builder.warehouseId);
        name = builder.name;
        location = builder.location;
        items = builder.items;
        capacity = builder.capacity;
        warehouseStatus = builder.warehouseStatus;
        operationalIssues = builder.operationalIssues;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public List<WarehouseItem> getItems() {
        return items;
    }

    public Capacity getCapacity() {
        return capacity;
    }

    public WarehouseStatus getWarehouseStatus() {
        return warehouseStatus;
    }

    public List<String> getOperationalIssues() {
        return operationalIssues;
    }

    public static final class Builder {
        private WarehouseId warehouseId;
        private String name;
        private Location location;
        private List<WarehouseItem> items;
        private Capacity capacity;
        private WarehouseStatus warehouseStatus;
        private List<String> operationalIssues;

        private Builder() {
        }

        public Builder warehouseId(WarehouseId val) {
            warehouseId = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder location(Location val) {
            location = val;
            return this;
        }

        public Builder items(List<WarehouseItem> val) {
            items = val;
            return this;
        }

        public Builder capacity(Capacity val) {
            capacity = val;
            return this;
        }

        public Builder warehouseStatus(WarehouseStatus val) {
            warehouseStatus = val;
            return this;
        }

        public Builder operationalIssues(List<String> val) {
            operationalIssues = val;
            return this;
        }

        public Warehouse build() {
            return new Warehouse(this);
        }
    }
}
