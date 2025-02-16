package com.sra.warehouse.service.dataaccess.warehouse.adapter;

import com.sra.domain.valueobject.WarehouseId;
import com.sra.warehouse.service.dataaccess.warehouse.mapper.WarehouseDataAccessMapper;
import com.sra.warehouse.service.dataaccess.warehouse.repository.WarehouseJpaRepository;
import com.sra.warehouse.service.domain.entity.Warehouse;
import com.sra.warehouse.service.domain.ports.output.repository.WarehouseRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WarehouseRepositoryImpl implements WarehouseRepository {

    private final WarehouseJpaRepository warehouseJpaRepository;
    private final WarehouseDataAccessMapper warehouseDataAccessMapper;

    public WarehouseRepositoryImpl(WarehouseJpaRepository warehouseJpaRepository,
                                   WarehouseDataAccessMapper warehouseDataAccessMapper) {
        this.warehouseJpaRepository = warehouseJpaRepository;
        this.warehouseDataAccessMapper = warehouseDataAccessMapper;
    }

    @Override
    public Warehouse save(Warehouse warehouse) {
        return warehouseDataAccessMapper.warehouseEntityToWarehouse(warehouseJpaRepository
                .save(warehouseDataAccessMapper.warehouseToWarehouseEntity(warehouse)));
    }

    @Override
    public Optional<Warehouse> findById(WarehouseId warehouseId) {
        return warehouseJpaRepository.findById(warehouseId.getValue()).map(warehouseDataAccessMapper::warehouseEntityToWarehouse);
    }
}