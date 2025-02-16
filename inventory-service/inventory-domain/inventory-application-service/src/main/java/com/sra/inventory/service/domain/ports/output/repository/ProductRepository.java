package com.sra.inventory.service.domain.ports.output.repository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    Optional<Product> findProductById(UUID productId);
}
