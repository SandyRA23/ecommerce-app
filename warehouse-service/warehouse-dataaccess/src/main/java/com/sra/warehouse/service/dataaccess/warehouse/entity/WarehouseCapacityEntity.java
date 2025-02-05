package com.sra.warehouse.service.dataaccess.warehouse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "warehouse_capacity")
@Entity
public class WarehouseCapacityEntity {
    @Id
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "WAREHOUSE_ID")
    private WarehouseEntity warehouse;

    private int maxCapacity;
    private int currentCapacity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseCapacityEntity that = (WarehouseCapacityEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}