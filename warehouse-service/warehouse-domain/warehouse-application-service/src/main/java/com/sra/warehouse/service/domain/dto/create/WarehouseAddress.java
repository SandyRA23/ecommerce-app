package com.sra.warehouse.service.domain.dto.create;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class WarehouseAddress {
    @NotNull
    @Max(value = 50)
    private final String address;
    @NotNull
    @Max(value = 50)
    private final String city;
    @NotNull
    @Max(value = 50)
    private final String country;
}
