package com.sra.inventory.service.domain.ports.output.repository;

import com.sra.inventory.service.domain.entity.Inventory;

public interface InventoryRepository {
    Inventory save(Inventory inventory);
}
