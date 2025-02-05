package com.sra.warehouse.service.dataaccess.warehouse.entity;

import com.sra.domain.valueobject.ProductId;
import com.sra.domain.valueobject.WarehouseId;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(WarehouseItemEntityId.class)
@Table(name = "warehouse_items")
@Entity
public class WarehouseItemEntity {
    @Id
    private Long id;
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "WAREHOUSE_ID")
    private WarehouseEntity warehouse;

    private UUID warehouseId;
    private UUID productId;
    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseItemEntity that = (WarehouseItemEntity) o;
        return id.equals(that.id) && warehouse.equals(that.warehouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, warehouse);
    }
}