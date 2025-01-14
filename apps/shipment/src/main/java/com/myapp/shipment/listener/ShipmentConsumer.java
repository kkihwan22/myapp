package com.myapp.shipment.listener;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.shipment.model.CheckoutRequest;
import com.myapp.shipment.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ShipmentConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(ShipmentConsumer.class);
    private static final String TOPIC_NAME = "checkout.complete.v1";
    private static final String GROUP_ID = "shipment.group.v1";

    private final ShipmentService shipmentService;
    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @KafkaListener(topics = TOPIC_NAME, groupId = GROUP_ID)
    public void recordListener(String jsonMessage) {
        try {
            CheckoutRequest request = objectMapper.readValue(jsonMessage, CheckoutRequest.class);
            LOG.info(request.toString());
            shipmentService.saveCheckOutData(request);
        } catch (Exception e) {
            LOG.error("recordListener ERROR message = {}", jsonMessage, e);
        }
    }
}
