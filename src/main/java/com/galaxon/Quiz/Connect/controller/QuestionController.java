package com.galaxon.Quiz.Connect.controller;

import com.galaxon.Quiz.Connect.constants.Routes;
import com.galaxon.Quiz.Connect.service.implementation.QuestionServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.BASE_API + Routes.Question.BASE_QUESTION)
@RequiredArgsConstructor
@Slf4j
public class QuestionController {

    private final QuestionServiceImpl questionService;

    @DeleteMapping(Routes.Question.QUESTION_ID)
    public boolean deleteQuestion(@PathVariable Long questionId) {
        return questionService.deleteQuestionById(questionId);
    }
}
