package com.myapp.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProducerController {

    private final ProduceService produceService;

    @GetMapping("/publish")
    public String publish(String message) {
        produceService.send(message);
        return "published a message :" + message;
    }

    @RequestMapping("/publish-callback")
    public String publishWithCallback(String message) {
        produceService.sendWithCallback(message);
        return "published a message with callback :" + message;
    }

    @RequestMapping("/publish-json")
    public String publishJson(@RequestBody MyMessage message) {
        produceService.sendJson(message);
        return "published a message with callback :" + message.getName() + "," + message.getMessage();
    }
}
