package com.galaxon.Quiz.Connect.controller;

import com.galaxon.Quiz.Connect.dto.request.QuestionRequest;
import com.galaxon.Quiz.Connect.dto.response.QuizResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.galaxon.Quiz.Connect.constant.Routes;
import com.galaxon.Quiz.Connect.service.implementation.QuestionServiceImpl;

import java.util.List;

@RestController
@RequestMapping(Routes.BASE_API + Routes.Question.BASE_QUESTION)
@RequiredArgsConstructor
@Slf4j
public class QuestionController {

    private final QuestionServiceImpl questionService;

    @PostMapping(Routes.Question.ADD_QUESTION)
    public QuizResponse addQuestionToQuiz(@PathVariable Long quizId, @Valid @RequestBody List<QuestionRequest> questionRequest) {
        return questionService.addQuestionToQuiz(quizId, questionRequest);
    }

    @DeleteMapping(Routes.Question.QUESTION_ID)
    public boolean deleteQuestion(@PathVariable Long questionId) {
        return questionService.deleteQuestionById(questionId);
    }
}
