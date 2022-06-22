package com.natixis.finatial.traning.vote.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController("/api")
@RequiredArgsConstructor
public class ApiController {

    @GetMapping("ping")
    public String send() {
        return "\"pong\"";
    }

}
