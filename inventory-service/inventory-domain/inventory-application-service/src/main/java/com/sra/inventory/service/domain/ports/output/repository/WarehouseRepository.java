package com.sra.inventory.service.domain.ports.output.repository;

import com.sra.inventory.service.domain.entity.Warehouse;

import java.util.Optional;
import java.util.UUID;

public interface WarehouseRepository {
    Optional<Warehouse> findWarehouseById(UUID warehouseId);
}
