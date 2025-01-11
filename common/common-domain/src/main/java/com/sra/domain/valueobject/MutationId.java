package com.sra.domain.valueobject;

import java.util.UUID;

public class MutationId extends BaseId<UUID> {
    public MutationId(UUID value){
        super(value);
    }
}
