package com.sra.warehouse.service.messaging.publisher.kafka;

import com.sra.kafka.producer.KafkaMessageHelper;
import com.sra.warehouse.service.domain.config.WarehouseServiceConfigData;
import com.sra.warehouse.service.messaging.mapper.WarehouseMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateProductMutationKafkaMessagePublisher implements ProductMutationRequestMessagePublisher {

    private final WarehouseMessagingDataMapper warehouseMessagingDataMapper;
    private final WarehouseServiceConfigData warehouseServiceConfigData;
    private final KafkaProducer<String, ProductMutationRequestAvroModel> kafkaProducer;
    private final KafkaMessageHelper kafkaMessageHelper;

    public CreateProductMutationKafkaMessagePublisher(WarehouseMessagingDataMapper warehouseMessagingDataMapper,
                                                      WarehouseServiceConfigData warehouseServiceConfigData,
                                                      KafkaProducer<String, ProductMutationRequestAvroModel> kafkaProducer,
                                                      KafkaMessageHelper kafkaMessageHelper) {
        this.warehouseMessagingDataMapper = warehouseMessagingDataMapper;
        this.warehouseServiceConfigData = warehouseServiceConfigData;
        this.kafkaProducer = kafkaProducer;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    public void publish(ProductMutationEvent domainEvent) {
        String productMutationId = domainEvent.getProductMutation().getId().toString();
        log.info("Received ProductMutationEvent for mutation id: {}", productMutationId);

        try {
            ProductMutationRequestAvroModel mutationRequestAvroModel =
                    warehouseMessagingDataMapper.productMutationEventToMutationRequestAvroModel(domainEvent);

            kafkaProducer.send(
                    warehouseServiceConfigData.getProductMutationRequestTopicName(),
                    productMutationId,
                    mutationRequestAvroModel,
                    kafkaMessageHelper.getKafkaCallback(
                            warehouseServiceConfigData.getProductMutationResponseTopicName(),
                            mutationRequestAvroModel,
                            productMutationId,
                            "ProductMutationRequestAvroModel"
                    )
            );

            log.info("ProductMutationRequestAvroModel sent to Kafka for mutation id: {}",
                    mutationRequestAvroModel.getId());

        } catch (Exception e) {
            log.error("Error while sending ProductMutationRequestAvroModel message to Kafka with mutation id: {}, error: {}",
                    productMutationId, e.getMessage());
        }
    }
}