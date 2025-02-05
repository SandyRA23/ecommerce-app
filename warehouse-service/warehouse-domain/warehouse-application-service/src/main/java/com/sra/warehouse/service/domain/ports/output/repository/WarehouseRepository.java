package com.sra.warehouse.service.domain.ports.output.repository;

import com.sra.domain.valueobject.WarehouseId;
import com.sra.warehouse.service.domain.entity.Warehouse;

import java.util.Optional;

public interface WarehouseRepository {
    Warehouse save(Warehouse warehouse);

    Optional<Warehouse> findById(WarehouseId warehouseId);
}
