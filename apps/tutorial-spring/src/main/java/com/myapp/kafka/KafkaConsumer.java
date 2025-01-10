package com.myapp.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class KafkaConsumer {
    private static final Logger log = LoggerFactory.getLogger("KafkaConsumer");
    private static final String TOPIC_NAME = "topic-tutorial";

    ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = TOPIC_NAME, groupId = "group-tutorial")
    public void listener(String jsonMessage) {
        try {
            MyMessage myMessage = objectMapper.readValue(jsonMessage, MyMessage.class);
            log.info(">>> {}, {}", myMessage.getName(), myMessage.getMessage());
        } catch (Exception e) {
            log.error("{}", e);
        }
    }
}
