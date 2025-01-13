package com.sra.inventory.service.domain.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class WarehouseResponse {
    private final String id;

    private final String productId;

    private final Instant createdAt;

    private final List<String> failureMessage;
}
