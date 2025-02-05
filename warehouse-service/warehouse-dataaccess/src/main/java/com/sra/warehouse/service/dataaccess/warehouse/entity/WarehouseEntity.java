package com.sra.warehouse.service.dataaccess.warehouse.entity;

import com.sra.warehouse.service.domain.entity.WarehouseItem;
import com.sra.warehouse.service.domain.valueobject.Capacity;
import com.sra.warehouse.service.domain.valueobject.Location;
import com.sra.warehouse.service.domain.valueobject.WarehouseStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "warehouses")
@Entity
public class WarehouseEntity {
    @Id
    private UUID id;
    private String name;
    @Enumerated(EnumType.STRING)
    private WarehouseStatus warehouseStatus;
    private String failureMessages;

    public static final String ISSUE_DELIMITER = ",";

    @OneToOne(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private WarehouseLocationEntity location;

    @OneToOne(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private WarehouseCapacityEntity capacity;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<WarehouseItemEntity> items;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseEntity that = (WarehouseEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}