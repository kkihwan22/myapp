package com.myapp.checkout.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.checkout.model.CheckoutSubmitRequest;
import com.myapp.checkout.repository.CheckoutRepository;
import com.myapp.checkout.service.entity.CheckoutEntity;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class SaveService {
    private final static Logger LOG = LoggerFactory.getLogger(SaveService.class);
    private final static String CHECKOUT_COMPLETE_TOPIC_NAME = "checkout.complete.v1";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private final CheckoutRepository checkoutRepository;

    public Long save(CheckoutSubmitRequest request) {
        CheckoutEntity entity = saveDatabase(request);
        request.setCheckoutId(entity.getCheckoutId());
        request.setCreatedAt(new Date(entity.getCreatedAt().getTime()));
        send(request);

        return entity.getCheckoutId();
    }

    private CheckoutEntity saveDatabase(CheckoutSubmitRequest request) {

        CheckoutEntity entity = new CheckoutEntity();
        entity.setCheckoutId(request.getCheckoutId());
        entity.setMemberId(request.getMemberId());
        entity.setProductId(request.getProductId());
        entity.setAmount(request.getAmount());
        entity.setShippingAddress(request.getShippingAddress());

        return checkoutRepository.save(entity);
    }

    private void send(CheckoutSubmitRequest request) {
        try {
            String jsonInString = objectMapper.writeValueAsString(request);
            kafkaTemplate.send(CHECKOUT_COMPLETE_TOPIC_NAME, jsonInString);
            LOG.info("success sendToKafka");
        } catch (Exception e) {
            LOG.error("sendToKafka", e);
        }
    }
}
