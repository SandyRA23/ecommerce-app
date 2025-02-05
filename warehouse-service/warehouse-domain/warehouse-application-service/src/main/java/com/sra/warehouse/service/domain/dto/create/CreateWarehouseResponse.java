package com.sra.warehouse.service.domain.dto.create;

import com.sra.warehouse.service.domain.valueobject.WarehouseStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class CreateWarehouseResponse {
    @NotNull
    private UUID warehouseId;
    @NotNull
    private final String name;
    @NotNull
    private final WarehouseStatus warehouseStatus;
}
