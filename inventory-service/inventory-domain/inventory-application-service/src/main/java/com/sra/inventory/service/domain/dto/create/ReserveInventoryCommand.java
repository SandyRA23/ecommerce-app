package com.sra.inventory.service.domain.dto.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class ReserveInventoryCommand {
    @NotNull
    private final UUID warehouseId;
    @NotNull
    private final List<InventoryReserveItem> items;

    @Getter
    @AllArgsConstructor
    @Builder
    public static class InventoryReserveItem {
        @NotNull
        private final UUID productId;
        @NotNull
        private final int reservedQuantity;
    }
}
