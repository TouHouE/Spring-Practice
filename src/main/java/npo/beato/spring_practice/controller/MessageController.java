package npo.beato.spring_practice.controller;

import npo.beato.spring_practice.model.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class MessageController {
    private static final String TEMPLATE = "Hello %s!";
    private final AtomicInteger counter = new AtomicInteger();

    @GetMapping("/message")
    public Message message(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Message(counter.incrementAndGet(), String.format(TEMPLATE, name));
    }


}
