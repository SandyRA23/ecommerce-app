package com.sra.inventory.service.domain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "inventory-service")
public class InventoryServiceConfigData {
    private String stockMutationTopicName;
    private String inventoryUpdateTopicName;
    private String stockLevelCheckTopicName;
    private String inventoryAlertTopicName;
}
