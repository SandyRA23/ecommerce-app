package com.sra.domain.valueobject;

import java.util.UUID;

public class StockMutationId extends BaseId<UUID> {
    public StockMutationId(UUID value){
        super(value);
    }
}
