package com.natixis.finatial.traning.voteapp.controllers;

import com.natixis.finatial.traning.voteapp.entities.question.Answer;
import com.natixis.finatial.traning.voteapp.entities.question.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/db/answers")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    @GetMapping
    public Iterable<Answer> findAll() {
        return answerService.findAll();
    }

}
