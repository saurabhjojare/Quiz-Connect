package com.galaxon.Quiz.Connect.controller;

import com.galaxon.Quiz.Connect.constants.Routes;
import com.galaxon.Quiz.Connect.dto.request.QuestionRequest;
import com.galaxon.Quiz.Connect.dto.request.QuizRequest;
import com.galaxon.Quiz.Connect.dto.request.SubmitQuizRequest;
import com.galaxon.Quiz.Connect.dto.response.QuizTakeResponse;
import com.galaxon.Quiz.Connect.dto.response.SubmitQuizResponse;
import com.galaxon.Quiz.Connect.model.Quiz;
import com.galaxon.Quiz.Connect.service.implementation.QuizServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.BASE_API + Routes.Quiz.BASE_QUIZ)
@RequiredArgsConstructor
@Slf4j
public class QuizController {

    private final QuizServiceImpl quizService;

    @PostMapping
    public Quiz createQuiz(@Valid @RequestBody QuizRequest quizRequest) {
        return quizService.createQuiz(quizRequest);
    }

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @PostMapping(Routes.Quiz.ADD_QUESTION)
    public Quiz addQuestionToQuiz(@PathVariable Long quizId, @Valid @RequestBody List<QuestionRequest> questionRequest) {
        return quizService.addQuestionToQuiz(quizId, questionRequest);
    }

    @GetMapping(Routes.Quiz.TAKE_QUIZ)
    public QuizTakeResponse takeQuiz(@PathVariable Long quizId) {
        return quizService.takeQuiz(quizId);
    }

    @PostMapping(Routes.Quiz.SUBMIT_QUIZ)
    public SubmitQuizResponse submitQuiz(@PathVariable Long quizId, @Valid @RequestBody SubmitQuizRequest request) {
        return quizService.submitQuiz(quizId, request);
    }
}
