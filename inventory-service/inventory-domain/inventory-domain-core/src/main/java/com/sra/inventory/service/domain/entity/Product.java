package com.sra.inventory.service.domain.entity;

import com.sra.domain.entity.BaseEntity;
import com.sra.domain.valueobject.ProductId;

import java.util.UUID;

public class Product extends BaseEntity<ProductId> {
    private String name;
    private String category;
    private String location;

    public Product(ProductId productId, String name, String category, String location) {
        super.setId(productId);
        this.name = name;
        this.category = category;
        this.location = location;
    }

    public Product(ProductId productId) {

        super.setId(productId);
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }
}