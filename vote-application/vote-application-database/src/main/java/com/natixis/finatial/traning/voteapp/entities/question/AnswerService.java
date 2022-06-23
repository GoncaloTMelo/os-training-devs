package com.natixis.finatial.traning.voteapp.entities.question;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class AnswerService {
    private final AnswerRepository repository;

    public Iterable<Answer> findAll() {
        return repository.findAll();
    }

}
