package com.natixis.finatial.traning.vote.backend.controllers;

import com.natixis.finatial.traning.vote.backend.services.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController("/")
@RequiredArgsConstructor
public class MainController {
    private final VoteService voteService;

    @GetMapping("ping")
    public String send() {
        return "\"pong\"";
    }


    @PostMapping("vote")
    public String vote(@RequestParam(required = false) String key, @RequestParam String value) {
        voteService.vote(key, value);
        return "\"ok\"";
    }
}
