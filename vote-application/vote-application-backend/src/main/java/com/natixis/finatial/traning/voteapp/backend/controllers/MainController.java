package com.natixis.finatial.traning.voteapp.backend.controllers;

import com.natixis.finatial.traning.voteapp.backend.services.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class MainController {
    private final VoteService voteService;
    @GetMapping()
    public String home() {
        return "\"home\"";
    }
    @GetMapping("ping")
    public String ping() {
        return "\"pong\"";
    }


    @PostMapping("vote")
    public String vote(@RequestParam(required = false) String key, @RequestParam String value) {
        voteService.vote(key, value);
        return "\"ok\"";
    }
}
