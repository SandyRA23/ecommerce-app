package com.sra.inventory.service.domain.dto.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class UpdateInventoryResponse {
    @NotNull
    private final UUID inventoryId;
    @NotNull
    private final String message;
}
