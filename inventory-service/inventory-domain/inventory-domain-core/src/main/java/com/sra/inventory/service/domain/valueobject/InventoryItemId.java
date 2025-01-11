package com.sra.inventory.service.domain.valueobject;

import com.sra.domain.valueobject.BaseId;

public class InventoryItemId extends BaseId<Long> {
    public InventoryItemId(Long value) {
        super(value);
    }
}