package com.natixis.finatial.traning.vote.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api")
@RequiredArgsConstructor

public class ApiController {

    @GetMapping()
    public String home() {
        return "\"api-home\"";
    }
    @GetMapping("ping")
    public String ping() {
        return "\"api-pong\"";
    }

}
