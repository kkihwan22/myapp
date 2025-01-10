package com.myapp.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
public class ProduceService {
    private static final String TOPIC_NAME = "topic-tutorial";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, MyMessage> myMessageKafkaTemplate;

    public void send(String message) {
        kafkaTemplate.send(TOPIC_NAME, message);
    }

    public void sendWithCallback(String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC_NAME, message);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                System.err.println("Failed to send message: " + message + ", Error: " + ex.getMessage());
            } else {
                RecordMetadata metadata = result.getRecordMetadata();
                System.out.println("Message sent: " + message +
                        ", Offset: " + metadata.offset() +
                        ", Partition: " + metadata.partition() +
                        ", Topic: " + metadata.topic());
            }
        });
    }

    public void sendJson(MyMessage message) {
        myMessageKafkaTemplate.send(TOPIC_NAME, message);
    }
}
