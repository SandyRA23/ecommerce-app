package com.sra.warehouse.service.domain.entity;

import com.sra.domain.entity.BaseEntity;
import com.sra.domain.valueobject.Money;
import com.sra.domain.valueobject.ProductId;

public class Product extends BaseEntity<ProductId> {
    private String name;
    private String category;
    private Money price;

    public Product(ProductId productId, String name, String category, Money price) {
        super.setId(productId);
        this.name = name;
        this.category = category;
        this.price = price;
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

    public Money getPrice() {
        return price;
    }
}
