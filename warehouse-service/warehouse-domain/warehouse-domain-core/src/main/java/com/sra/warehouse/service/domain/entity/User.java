package com.sra.warehouse.service.domain.entity;

import com.sra.domain.entity.BaseEntity;
import com.sra.domain.valueobject.UserId;

public class User extends BaseEntity<UserId> {
    private String name;
    private String role;

    public User(UserId userId, String name, String role) {
        super.setId(userId);
        this.name = name;
        this.role = role;
    }

    public User(UserId userId) {

        super.setId(userId);
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
