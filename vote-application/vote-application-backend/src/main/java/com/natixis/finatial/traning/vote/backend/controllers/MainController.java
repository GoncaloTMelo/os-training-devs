package com.natixis.finatial.traning.vote.backend.controllers;

import com.natixis.finatial.traning.vote.backend.services.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController("/")
@RequiredArgsConstructor
public class MainController {
    private final KafkaProducerService producerService;

    @GetMapping("ping")
    public String send() {
        return "pong";
    }

    @GetMapping("send/{value}")
    public String send(@PathVariable String value) {
        producerService.send(value);
        return "ok";
    }

    @GetMapping("send/{key}/{value}")
    public String send(@PathVariable String key, @PathVariable String value) {
        producerService.send(key, value);
        return "ok";
    }
}
