package com.sra.warehouse.service.dataaccess.warehouse.repository;

import com.sra.domain.valueobject.WarehouseId;
import com.sra.warehouse.service.dataaccess.warehouse.entity.WarehouseEntity;
import com.sra.warehouse.service.domain.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WarehouseJpaRepository extends JpaRepository<WarehouseEntity, UUID> {
    //Optional<WarehouseEntity> findById(UUID warehouseId);

}
