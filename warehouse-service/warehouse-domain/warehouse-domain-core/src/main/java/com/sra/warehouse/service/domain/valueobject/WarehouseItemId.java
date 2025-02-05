package com.sra.warehouse.service.domain.valueobject;

import com.sra.domain.valueobject.BaseId;

import java.util.UUID;

public class WarehouseItemId extends BaseId<Long> {
    public WarehouseItemId(Long value) {
        super(value);
    }
}
