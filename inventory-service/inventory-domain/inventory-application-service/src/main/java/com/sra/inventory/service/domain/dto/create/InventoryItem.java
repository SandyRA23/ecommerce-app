package com.sra.inventory.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class InventoryItem {
    @NotNull
    private final UUID productId;
    @NotNull
    private final UUID warehouseId;
    @NotNull
    private final int stock;
    @NotNull
    private final int reservedStock;
}
