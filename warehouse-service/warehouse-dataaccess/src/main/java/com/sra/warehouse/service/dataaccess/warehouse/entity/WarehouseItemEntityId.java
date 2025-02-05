package com.sra.warehouse.service.dataaccess.warehouse.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseItemEntityId implements Serializable {

    private Long id;
    private WarehouseEntity warehouse;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseItemEntityId that = (WarehouseItemEntityId) o;
        return id.equals(that.id) && warehouse.equals(that.warehouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, warehouse);
    }
}